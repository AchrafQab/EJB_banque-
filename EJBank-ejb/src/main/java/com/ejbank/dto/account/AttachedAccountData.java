/**
 * Represents details of an attached account, including validation requirements.
 */
package com.ejbank.dto.account;

import java.math.BigDecimal;
import java.util.Objects;

public class AttachedAccountData {

    private final Integer id; // Account id
    private final String userFirstname; // First name of the user associated with the account
    private final String type; // Account type
    private final BigDecimal amount; // Account balance
    private final int validation; // Number of required validations for this account

    public AttachedAccountData(Integer id, String userFirstname, String type, BigDecimal amount, int validation) {
        this.id = id;
        this.userFirstname = Objects.requireNonNull(userFirstname, "User cannot be null");
        this.type = type;
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.validation = validation;
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

    public int getValidation() {
        return validation;
    }
}