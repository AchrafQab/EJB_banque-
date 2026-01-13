/**
 * Represents basic information about an account, including its ID, type, and balance.
 */
package com.ejbank.dto.account;

import java.math.BigDecimal;

public class AccountData {
    private final Integer id; // Account id
    private final String type; // Account type
    private final BigDecimal amount; // Account balance

    public AccountData(Integer id, String type, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}