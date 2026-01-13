/**
 * Represents the response payload for a transaction application.
 * Indicates the result of the application and an accompanying message.
 */
package com.ejbank.api.transaction.payload;

public class TransactionApplyResponsePayload {
    private final boolean result;
    private final String message;

    public TransactionApplyResponsePayload(boolean result, String message) {
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
