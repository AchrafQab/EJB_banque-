/**
 * Represents detailed account information, including the owner, advisor,
 * rate, interest, balance, and any error messages.
 */
package com.ejbank.dto.account;

import java.math.BigDecimal;

public class AccountDetailsData {
    private final String owner; // Account owner information
    private final String advisor; // Account advisor information
    private final BigDecimal rate; // Account interest rate
    private final BigDecimal interest; // Calculated interest
    private final BigDecimal amount; // Account balance
    private final String error; // Error message

    public AccountDetailsData(String owner, String advisor, BigDecimal rate, BigDecimal interest, BigDecimal amount, String error) {
        this.owner = owner;
        this.advisor = advisor;
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
