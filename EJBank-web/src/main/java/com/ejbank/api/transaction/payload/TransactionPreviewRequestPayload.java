/**
 * Represents the payload for a transaction preview request.
 * Contains details about the source, destination, amount, and author of the transaction.
 */
package com.ejbank.api.transaction.payload;

import java.math.BigDecimal;

public class TransactionPreviewRequestPayload {
    private int source;
    private int destination;
    private BigDecimal amount;
    private int author;

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
