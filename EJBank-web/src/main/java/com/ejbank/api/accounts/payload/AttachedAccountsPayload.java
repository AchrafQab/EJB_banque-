/**
 * Represents the payload for a list of attached accounts.
 * Includes the list of attached account details and any associated error messages.
 */
package com.ejbank.api.accounts.payload;

import java.util.List;

public class AttachedAccountsPayload {
    private final List<AttachedAccountPayload> accounts;
    private final String error;

    public AttachedAccountsPayload(List<AttachedAccountPayload> accounts, String error) {
        this.accounts = accounts;
        this.error = error;
    }

    public List<AttachedAccountPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
