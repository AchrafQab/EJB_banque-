/**
 * Represents the payload for an attached account.
 * Includes account ID, user information, account type, balance, and validation count.
 */
package com.ejbank.api.accounts.payload;

import java.math.BigDecimal;
import java.util.Objects;

public class AttachedAccountPayload {

    private final Integer id;
    private final String user;
    private final String type;
    private final BigDecimal amount;
    private final int validation;

    public AttachedAccountPayload(Integer id, String user, String type, BigDecimal amount, int validation) {
        this.id = id;
        this.user = Objects.requireNonNull(user, "User cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.amount = amount;
        this.validation = validation;
    }

    public Integer getId() {
        return id;
    }

    public String getUser() {
        return user;
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
