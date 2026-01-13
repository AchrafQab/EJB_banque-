
/**
 * Represents a list of accounts with basic details and an optional error message.
 */
package com.ejbank.dto.account;

import java.util.List;
import java.util.Objects;

public class AccountsData {

    private final List<AccountData> accounts; // List of basic account data
    private final String error; // Error message

    public AccountsData(List<AccountData> accounts, String error) {
        this.accounts = Objects.requireNonNull(accounts, "Accounts cannot be null");
        this.error = error;
    }

    public List<AccountData> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "{" + "accounts=" + accounts + ", error='" + error + '\'' + '}';
    }
}