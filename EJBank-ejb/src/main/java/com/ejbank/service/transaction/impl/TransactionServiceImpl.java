/**
 * Implementation of the TransactionService interface.
 */
package com.ejbank.service.transaction.impl;

import com.ejbank.dao.*;
import com.ejbank.dto.transaction.*;
import com.ejbank.service.transaction.TransactionService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TransactionServiceImpl implements TransactionService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public TransactionListData getTransactions(Integer accountId, Integer offset, Integer userId) {
        if (accountId == null || userId == null || offset < 0) {
            return new TransactionListData(0, List.of(), "Invalid input parameters.");
        }

        EjbankAccount account = em.find(EjbankAccount.class, accountId);
        if (account == null) {
            return new TransactionListData(0, List.of(), "Account not found.");
        }

        List<EjbankTransaction> transactions = em.createQuery(
                        "SELECT t FROM EjbankTransaction t WHERE t.accountFrom.id = :accountId OR t.accountTo.id = :accountId ORDER BY t.date DESC",
                        EjbankTransaction.class)
                .setParameter("accountId", accountId)
                .setFirstResult(offset)
                .setMaxResults(10)
                .getResultList();

        List<TransactionData> transactionDataList = transactions.stream()
                .map(this::mapToTransactionData)
                .collect(Collectors.toList());

        int total = ((Number) em.createQuery(
                        "SELECT COUNT(t) FROM EjbankTransaction t WHERE t.accountFrom.id = :accountId OR t.accountTo.id = :accountId")
                .setParameter("accountId", accountId)
                .getSingleResult()).intValue();

        return new TransactionListData(total, transactionDataList, null);
    }

    private TransactionData mapToTransactionData(EjbankTransaction transaction) {
        String sourceLabel = transaction.getAccountFrom().getAccountType().getName();
        String destinationLabel = transaction.getAccountTo().getAccountType().getName();
        String destinationUser = transaction.getAccountTo().getCustomer().getFirstname() + " " + transaction.getAccountTo().getCustomer().getLastname();
        String authorName = transaction.getAuthor().getFirstname() + " " + transaction.getAuthor().getLastname();

        String state;
        if (transaction.getApplied()) {
            state = "APPLYED";
        } else if (transaction.getAccountFrom().getCustomer().getAdvisor() != null) {
            state = "WAITING_APPROVE";
        } else {
            state = "TO_APPROVE";
        }

        return new TransactionData(
                transaction.getId().longValue(),
                transaction.getDate().toString(),
                sourceLabel,
                destinationLabel,
                destinationUser,
                transaction.getAmount(),
                authorName,
                transaction.getComment(),
                state
        );
    }


    public TransactionPreviewData previewTransaction(Integer sourceAccountId, Integer destinationAccountId, BigDecimal amount, Integer authorId) {

        if (sourceAccountId == null || destinationAccountId == null || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionPreviewData(false, null, null, "Invalid input parameters", null);
        }

        EjbankAccount sourceAccount = em.find(EjbankAccount.class, sourceAccountId);
        if (sourceAccount == null) {
            return new TransactionPreviewData(false, null, null, "Source account not found", null);
        }

        EjbankAccount destinationAccount = em.find(EjbankAccount.class, destinationAccountId);
        if (destinationAccount == null) {
            return new TransactionPreviewData(false, null, null, "Destination account not found", null);
        }

        BigDecimal before = sourceAccount.getBalance();
        BigDecimal after = before.subtract(amount);

        if (after.compareTo(BigDecimal.ZERO) < 0) {
            return new TransactionPreviewData(false, before, after, "Insufficient funds in the source account", null);
        }

        return new TransactionPreviewData(true, before, after, "Transaction can proceed", null);
    }

    @Override
    public TransactionApplyData applyTransaction(Integer sourceAccountId, Integer destinationAccountId, BigDecimal amount, String comment, Integer authorId) {
        EjbankAccount sourceAccount = em.find(EjbankAccount.class, sourceAccountId);
        EjbankAccount destinationAccount = em.find(EjbankAccount.class, destinationAccountId);

        if (sourceAccount == null || destinationAccount == null) {
            return new TransactionApplyData(false, "One or both accounts do not exist.");
        }

        BigDecimal beforeBalance = sourceAccount.getBalance();
        if (beforeBalance.compareTo(amount) < 0) {
            return new TransactionApplyData(false, "Insufficient balance in the source account.");
        }

        boolean needsValidation = sourceAccount.getCustomer().getAdvisor() != null;

        EjbankTransaction transaction = new EjbankTransaction(
                sourceAccount,
                destinationAccount,
                em.find(EjbankUser.class, authorId),
                amount,
                comment,
                !needsValidation,
                new Date()
        );
        em.persist(transaction);

        if (needsValidation) {
            return new TransactionApplyData(true, "Transaction created successfully. It requires validation by an advisor.");
        }

        sourceAccount.setBalance(beforeBalance.subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        em.merge(sourceAccount);
        em.merge(destinationAccount);

        return new TransactionApplyData(true, "Transaction successfully applied.");
    }

    @Override
    public TransactionValidationData validateTransaction(Integer transactionId, boolean isApprove, Integer authorId) {
        var userDao = em.find(EjbankUser.class, authorId);

        if (userDao == null || !(userDao instanceof EjbankAdvisor)) {
            return new TransactionValidationData(false, "", "Error - Invalid advisor ID or not an advisor");
        }

        var transaction = em.find(EjbankTransaction.class, transactionId);
        if (transaction == null) {
            return new TransactionValidationData(false, "", "Error - Transaction does not exist");
        }

        var accountFrom = transaction.getAccountFrom();
        if (!accountFrom.getCustomer().getAdvisor().equals(userDao)) {
            return new TransactionValidationData(false, "", "Error - Advisor is not in charge of the client who initiated this transaction");
        }

        if (isApprove) {
            var accountTo = transaction.getAccountTo();
            var balanceFrom = accountFrom.getBalance();
            var balanceTo = accountTo.getBalance();
            var amount = transaction.getAmount();

            if (balanceFrom.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
                return new TransactionValidationData(false, "", "Error - Insufficient balance in the source account");
            }

            transaction.setApplied(true);
            accountFrom.setBalance(balanceFrom.subtract(amount));
            accountTo.setBalance(balanceTo.add(amount));

            em.merge(transaction);
            em.merge(accountFrom);
            em.merge(accountTo);

            return new TransactionValidationData(true, "Transaction approved successfully", null);
        } else {
            em.remove(transaction);
            return new TransactionValidationData(true, "Transaction rejected and canceled", null);
        }
    }


    public Long countPendingTransactionsForAdvisor(Integer advisorId) {
        if (advisorId == null) {
            return 0L;
        }

        var user = em.find(EjbankUser.class, advisorId);

        if (user == null) {
            return 0L;
        }

        if (user instanceof EjbankCustomer) {
            return 0L;
        }

        if (user instanceof EjbankAdvisor) {
            return em.createQuery(
                            "SELECT COUNT(t) " +
                                    "FROM EjbankTransaction t " +
                                    "WHERE t.accountFrom.customer.advisor.id = :advisorId AND t.applied = false", Long.class)
                    .setParameter("advisorId", advisorId)
                    .getSingleResult();
        }

        return 0L;
    }

}

