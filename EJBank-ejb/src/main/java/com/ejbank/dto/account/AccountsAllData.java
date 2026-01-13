/**
 * Represents a list of all accounts and an optional error message.
 */
package com.ejbank.dto.account;

import java.util.List;
import java.util.Objects;

public class AccountsAllData {

    private final List<AccountAllData> accounts; // List of all the accounts
    private final String error; // Error message

    public AccountsAllData(List<AccountAllData> accounts, String error) {
        this.accounts = Objects.requireNonNull(accounts, "Accounts cannot be null");
        this.error = error;
    }

    public List<AccountAllData> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}