package com.ejbank.api.payload;

public class TransactionResponse {

    private boolean result;
    private String message;

    public TransactionResponse(boolean result, String message) {
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
