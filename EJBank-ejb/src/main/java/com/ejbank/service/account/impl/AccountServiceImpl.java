/**
 * Implementation of the AccountService interface.
 */
package com.ejbank.service.account.impl;

import com.ejbank.dao.EjbankAccount;
import com.ejbank.dao.EjbankAdvisor;
import com.ejbank.dao.EjbankCustomer;
import com.ejbank.dao.EjbankUser;
import com.ejbank.dto.account.*;
import com.ejbank.service.account.AccountService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AccountsData getAllAccountsByUserId(Integer userId) {
        if (userId == null || userId <= 0) {
            return new AccountsData(List.of(), "Invalid user ID provided.");
        }

        var user = em.find(EjbankUser.class, userId);

        if(user instanceof EjbankCustomer customer) {
            return getCustomerAccountsData(customer);
        }

        if(user instanceof EjbankAdvisor advisor) {
            return getAdvisorAccountsData(advisor);
        }

        return new AccountsData(List.of(), "Error - User does not have any accounts.");
    }

    private AccountsData getCustomerAccountsData(EjbankCustomer customer) {
        List<AccountData> accounts = customer.getAccounts().stream()
                .map(account -> new AccountData(
                        account.getId(),
                        account.getAccountType().getName(),
                        account.getBalance()
                ))
                .collect(Collectors.toList());

        return new AccountsData(accounts, null);
    }

    private AccountsData getAdvisorAccountsData(EjbankAdvisor advisor) {
        List<AccountData> accounts = advisor.getCustomers().stream()
                .flatMap(customer -> customer.getAccounts().stream()
                        .map(account -> new AccountData(
                                account.getId(),
                                account.getAccountType().getName(),
                                account.getBalance()
                        )))
                .collect(Collectors.toList());

        return new AccountsData(accounts, null);
    }

    @Override
    public AttachedAccountsData getAttachedAccountsByUserId(Integer userId) {
        if (userId == null || userId <= 0) {
            return new AttachedAccountsData(List.of(), "Invalid user ID provided.");
        }

        EjbankUser user = em.find(EjbankUser.class, userId);
        if (user == null || !(user instanceof EjbankAdvisor advisor)) {
            return new AttachedAccountsData(List.of(), "User is not an advisor or does not exist.");
        }

        List<AttachedAccountData> attachedAccounts = new ArrayList<>();
        for (EjbankCustomer customer : advisor.getCustomers()) {
            for (EjbankAccount account : customer.getAccounts()) {
                attachedAccounts.add(mapToAttachedAccountData(account, customer));
            }
        }
        return new AttachedAccountsData(attachedAccounts, null);
    }

    private AttachedAccountData mapToAttachedAccountData(EjbankAccount account, EjbankCustomer customer) {

        var user = formatUserName(customer);
        int validationCount = getPendingValidations(account.getId());

        return new AttachedAccountData(
                account.getId(),
                user,
                account.getAccountType().getName(),
                account.getBalance(),
                validationCount
        );
    }

    private int getPendingValidations(Integer accountId) {
        Long count = em.createQuery(
                "SELECT COUNT(t) FROM EjbankTransaction t " +
                        "WHERE (t.accountFrom.id = :accountId OR t.accountTo.id = :accountId) " +
                        "AND t.applied = false", Long.class
        ).setParameter("accountId", accountId).getSingleResult();
        return count != null ? count.intValue() : 0;
    }

    @Override
    public AccountsAllData getAllAccounts(Integer userId) {

        if (userId == null || userId <= 0) {
            return new AccountsAllData(List.of(), "Invalid user ID provided.");
        }

        EjbankUser user = em.find(EjbankUser.class, userId);

        if (user == null) {
            return new AccountsAllData(List.of(), "User does not exist.");
        }

        List<AccountAllData> accounts = (user instanceof EjbankCustomer customer)
                ? getCustomerAccounts(customer)
                : (user instanceof EjbankAdvisor advisor)
                ? getAdvisorCustomerAccounts(advisor)
                : List.of();

        if (accounts.isEmpty()) {
            return new AccountsAllData(accounts, "No accounts found for the provided user.");
        }

        return new AccountsAllData(accounts, null);
    }

    private List<AccountAllData> getCustomerAccounts(EjbankCustomer customer) {
        return customer.getAccounts().stream()
                .map(account -> new AccountAllData(
                        account.getId(),
                        formatUserName(customer),
                        account.getAccountType().getName(),
                        account.getBalance()
                ))
                .toList();
    }

    private List<AccountAllData> getAdvisorCustomerAccounts(EjbankAdvisor advisor) {
        return advisor.getCustomers().stream()
            .flatMap(customer -> customer.getAccounts().stream()
                .map(account -> new AccountAllData(
                    account.getId(),
                    formatUserName(customer),
                    account.getAccountType().getName(),
                    account.getBalance()
                )))
            .toList();
    }

    private String formatUserName(EjbankCustomer customer) {
        return customer.getFirstname() + " " + customer.getLastname() + "(" + customer.getFirstname() + ")";
    }

    @Override
    public AccountDetailsData getAccountDetails(Integer accountId, Integer userId) {
        if (accountId == null || accountId <= 0 || userId == null || userId <= 0) {
            return new AccountDetailsData(null, null, null, null, null, "Invalid account or user ID.");
        }

        EjbankAccount account = em.find(EjbankAccount.class, accountId);
        if (account == null) {
            return new AccountDetailsData(null, null, null, null, null, "Account not found.");
        }

        EjbankCustomer owner = account.getCustomer();
        if (owner == null) {
            return new AccountDetailsData(null, null, null, null, null, "Owner not found.");
        }

        EjbankAdvisor advisor = owner.getAdvisor();
        if (advisor == null) {
            return new AccountDetailsData(null, null, null, null, null, "Advisor not found.");
        }

        if (!userId.equals(owner.getId()) && (advisor.getId() == null || !userId.equals(advisor.getId()))) {
            return new AccountDetailsData(null, null, null, null, null, "User does not have access to this account.");
        }

        BigDecimal rate = account.getAccountType().getRate();
        BigDecimal interest = account.getBalance().multiply(rate).divide(BigDecimal.valueOf(100));

        String formattedOwner = formatUser(owner, "client");
        String formattedAdvisor = formatUser(advisor, "conseillé");

        return new AccountDetailsData(formattedOwner, formattedAdvisor, rate, interest, account.getBalance(), null);
    }

    private String formatUser(EjbankUser user, String role) {
        return user.getFirstname() + " " + user.getLastname() + " (" + role + ")";
    }

}