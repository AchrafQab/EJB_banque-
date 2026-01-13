/**
 * Represents the payload for a list of all accounts.
 * Includes a list of account details and any associated error messages.
 */
package com.ejbank.api.accounts.payload;

import java.util.List;
import java.util.Objects;

public class AccountsAllPayload {
    private final List<AccountAllPayload> accounts;
    private final String error;

    public AccountsAllPayload(List<AccountAllPayload> accounts, String error) {
        this.accounts = Objects.requireNonNull(accounts, "Accounts cannot be null");
        this.error = error;
    }

    public List<AccountAllPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
