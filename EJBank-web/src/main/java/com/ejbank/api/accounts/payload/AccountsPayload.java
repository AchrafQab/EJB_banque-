/**
 * Represents the payload for a list of accounts.
 * Includes account information and associated error messages.
 */
package com.ejbank.api.accounts.payload;

import java.util.List;
import java.util.Objects;

public class AccountsPayload {
    private final List<AccountPayload> accounts;
    private final String error;

    public AccountsPayload(List<AccountPayload> accounts, String error) {
        this.accounts = Objects.requireNonNull(accounts);
        this.error = error;
    }

    public List<AccountPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
