/**
 * Represents the result of a transaction validation attempt, including
 * the success status, a message, and an error message if applicable.
 */
package com.ejbank.dto.transaction;

public class TransactionValidationData {
    private final boolean result; // tells whether the transaction validation succeeded
    private final String message; // Informational message about the validation
    private final String error; // Error message

    public TransactionValidationData(boolean result, String message, String error) {
        this.result = result;
        this.message = message;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}