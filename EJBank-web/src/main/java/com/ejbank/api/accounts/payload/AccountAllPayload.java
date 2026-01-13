/**
 * Represents the payload for detailed account information.
 * Includes account ID, user information, account type, and balance.
 */
package com.ejbank.api.accounts.payload;

import java.math.BigDecimal;

public class AccountAllPayload {
    private final Integer id;
    private final String user;
    private final String type;
    private final BigDecimal amount;

    public AccountAllPayload(Integer id, String user, String type, BigDecimal amount) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.amount = amount;
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
}
