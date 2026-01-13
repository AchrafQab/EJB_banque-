/**
 * Represents the payload for a transaction validation request.
 * Contains the transaction ID, approval status, and the author ID.
 */
package com.ejbank.api.transaction.payload;

public class TransactionValidationRequestPayload {
    private int transaction;
    private boolean approve;
    private int author;

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
