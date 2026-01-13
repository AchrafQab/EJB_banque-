/**
 * Represents the payload for basic account information.
 * Includes account ID, type, and balance.
 */
package com.ejbank.api.accounts.payload;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountPayload {
    private final Integer id;
    private final String type;
    private final BigDecimal amount;

    public AccountPayload(Integer id, String type, BigDecimal amount) {
        this.id = id;
        this.type = Objects.requireNonNull(type, "Type cannot be null");
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
