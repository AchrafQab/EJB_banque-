/**
 * Represents a detailed view of an account, including the associated user's first name,
 * account type, and balance.
 */
package com.ejbank.dto.account;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountAllData {

    private final Integer id; // Account id
    private final String userFirstname; // First name of the user associated to the account
    private final String type; // Account type
    private final BigDecimal amount; // Account balance

    public AccountAllData(Integer id, String userFirstname, String type, BigDecimal amount) {
        this.id = id;
        this.userFirstname = Objects.requireNonNull(userFirstname, "User cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}