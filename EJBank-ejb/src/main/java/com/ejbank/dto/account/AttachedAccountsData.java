/**
 * Represents a list of attached accounts and an optional error message.
 */
package com.ejbank.dto.account;

import java.util.List;
import java.util.Objects;

public class AttachedAccountsData {

    private final List<AttachedAccountData> accounts; // List of attached accounts
    private final String error; // Error message

    public AttachedAccountsData(List<AttachedAccountData> accounts, String error) {
        this.accounts = Objects.requireNonNull(accounts, "Accounts cannot be null");
        this.error = error;
    }

    public List<AttachedAccountData> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}