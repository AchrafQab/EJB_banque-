/**
 * Service interface for handling transactions.
 */
package com.ejbank.service.transaction;

import com.ejbank.dto.transaction.*;
import java.math.BigDecimal;

public interface TransactionService {

    /**
     * Retrieves a list of transactions for a given account.
     */
    TransactionListData getTransactions(Integer accountId, Integer offset, Integer userId);

    /**
     * Previews a transaction between two accounts.
     */
    TransactionPreviewData previewTransaction(Integer sourceAccountId, Integer destinationAccountId, BigDecimal amount, Integer authorId);

    /**
     * Applies a transaction between two accounts.
     */
    TransactionApplyData applyTransaction(Integer sourceAccountId, Integer destinationAccountId, BigDecimal amount, String comment, Integer authorId);

    /**
     * Validates a transaction.
     */
    TransactionValidationData validateTransaction(Integer transactionId, boolean approve, Integer authorId);

    /**
     * Counts pending transactions for a given advisor.
     */
    Long countPendingTransactionsForAdvisor(Integer advisorId);
}
