/**
 * Represents the result of a transaction application attempt, including
 * the success status and an accompanying message.
 */
package com.ejbank.dto.transaction;

public class TransactionApplyData {
    private final boolean result; // Tells whether the transaction was successfully applied
    private final String message; // Message about the transaction result

    public TransactionApplyData(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}