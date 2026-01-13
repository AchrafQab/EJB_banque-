/**
 * Represents the response payload for a transaction preview.
 * Provides details about the transaction status, before and after balances, and any messages or errors.
 */
package com.ejbank.api.transaction.payload;

import java.math.BigDecimal;

public class TransactionPreviewResponsePayload {
    private final boolean result;
    private final BigDecimal before;
    private final BigDecimal after;
    private final String message;
    private final String error;

    public TransactionPreviewResponsePayload(boolean result, BigDecimal before, BigDecimal after, String message, String error) {
        this.result = result;
        this.before = before;
        this.after = after;
        this.message = message;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public BigDecimal getBefore() {
        return before;
    }

    public BigDecimal getAfter() {
        return after;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
