/**
 * Represents a list of transactions with the total count and an optional error message.
 */
package com.ejbank.dto.transaction;

import java.util.List;

public class TransactionListData {
    private final int total; // Total number of transactions
    private final List<TransactionData> transactions; // List of transaction details
    private final String error; // Error message

    public TransactionListData(int total, List<TransactionData> transactions, String error) {
        this.total = total;
        this.transactions = transactions;
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public List<TransactionData> getTransactions() {
        return transactions;
    }

    public String getError() {
        return error;
    }
}
