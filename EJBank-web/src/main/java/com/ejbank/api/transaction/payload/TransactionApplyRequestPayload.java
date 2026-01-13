/**
 * Represents the payload for a transaction application request.
 * Contains details about the source, destination, amount, comment, and author of the transaction.
 */
package com.ejbank.api.transaction.payload;

import java.math.BigDecimal;

public class TransactionApplyRequestPayload {
    private int source;
    private int destination;
    private BigDecimal amount;
    private String comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
