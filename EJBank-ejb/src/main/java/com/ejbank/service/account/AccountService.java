/**
 * Service interface for managing accounts.
 */
package com.ejbank.service.account;

import com.ejbank.dto.account.*;

import javax.ejb.Local;

@Local
public interface AccountService {

    /**
     * Retrieves all accounts for a specific user using an id.
     */
    AccountsData getAllAccountsByUserId(Integer userId);

    /**
     * Retrieves attached accounts for a user by his id.
     */
    AttachedAccountsData getAttachedAccountsByUserId(Integer userId);

    /**
     * Retrieves all accounts for a user, including additional details.
     */
    AccountsAllData getAllAccounts(Integer userId);

    /**
     * Retrieves detailed information about a specific account.
     */
    AccountDetailsData getAccountDetails(Integer accountId, Integer userId);
}
