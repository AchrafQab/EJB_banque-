/**
 * Represents the response payload for a transaction validation.
 * Indicates the result of the validation, a message, and any errors.
 */
package com.ejbank.api.transaction.payload;

public class TransactionValidationResponsePayload {
    private final boolean result;
    private final String message;
    private final String error;

    public TransactionValidationResponsePayload(boolean result, String message, String error) {
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
