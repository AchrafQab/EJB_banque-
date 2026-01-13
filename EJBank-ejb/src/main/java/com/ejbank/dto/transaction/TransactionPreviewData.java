/**
 * Represents a preview of a transaction, showing the result of the validation,
 * the balance before and after the transaction, and any associated messages or errors.
 */
package com.ejbank.dto.transaction;

import java.math.BigDecimal;

public class TransactionPreviewData {
    private final boolean result; // whether the transaction is valid
    private final BigDecimal before; // Account balance before the transaction
    private final BigDecimal after; // Account balance after the transaction
    private final String message; // Informational message about the transaction preview
    private final String error; // Error message

    public TransactionPreviewData(boolean result, BigDecimal before, BigDecimal after, String message, String error) {
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