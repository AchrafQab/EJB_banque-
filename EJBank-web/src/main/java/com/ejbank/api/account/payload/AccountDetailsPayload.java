/**
 * Represents the payload for account details returned by the API.
 * This includes information about the account owner, advisor, rate, interest, amount, and any errors.
 */
package com.ejbank.api.account.payload;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDetailsPayload {

    private final String owner;
    private final String advisor;
    private final BigDecimal rate;
    private final BigDecimal interest;
    private final BigDecimal amount;
    private final String error;

    public AccountDetailsPayload(String owner, String advisor, BigDecimal rate, BigDecimal interest, BigDecimal amount, String error) {
        this.owner = Objects.requireNonNull(owner, "The owner cannot be null");
        this.advisor = Objects.requireNonNull(advisor, "The advisor cannot be null");
        this.rate = rate;
        this.interest = interest;
        this.amount = amount;
        this.error = error;
    }

    public String getOwner() {
        return owner;
    }

    public String getAdvisor() {
        return advisor;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getError() {
        return error;
    }
}
