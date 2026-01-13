package com.ejbank.api.payload;

public class TransactionCountResponse {

    private int count;

    public TransactionCountResponse(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
