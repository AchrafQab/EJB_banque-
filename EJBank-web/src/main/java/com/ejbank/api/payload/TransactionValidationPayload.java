package com.ejbank.api.payload;

public class TransactionValidationPayload {

    private Long transaction;
    private Boolean approve;

    // Getters and Setters
    public Long getTransaction() {
        return transaction;
    }

    public void setTransaction(Long transaction) {
        this.transaction = transaction;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }
}
