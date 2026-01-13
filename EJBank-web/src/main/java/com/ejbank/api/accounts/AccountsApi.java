/**
 * API for managing account-related operations.
 * Provides endpoints for retrieving accounts based on user ID.
 */
package com.ejbank.api.accounts;

import com.ejbank.api.accounts.payload.*;
import com.ejbank.service.account.AccountService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountsApi {

    @EJB
    private AccountService accountService;

    /**
     * Retrieves accounts for a specific user by ID.
     */
    @GET
    @Path("/{user_id}")
    public AccountsPayload getAccountsByUserId(@PathParam("user_id") Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive non-null value");
        }

        var accountsData = accountService.getAllAccountsByUserId(userId);

        List<AccountPayload> accounts = accountsData.getAccounts().stream()
                .map(account -> new AccountPayload(account.getId(), account.getType(), account.getAmount()))
                .collect(Collectors.toList());

        return new AccountsPayload(accounts, accountsData.getError());
    }

    /**
     * Retrieves attached accounts for a specific user by ID.
     */
    @GET
    @Path("/attached/{user_id}")
    public AttachedAccountsPayload getAttachedAccounts(@PathParam("user_id") Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive non-null value.");
        }

        var attachedAccountsData = accountService.getAttachedAccountsByUserId(userId);

        List<AttachedAccountPayload> payloads = attachedAccountsData.getAccounts().stream()
                .map(account -> new AttachedAccountPayload(
                        account.getId(),
                        account.getUserFirstname(),
                        account.getType(),
                        account.getAmount(),
                        account.getValidation()
                ))
                .collect(Collectors.toList());

        return new AttachedAccountsPayload(payloads, attachedAccountsData.getError());
    }

    /**
     * Retrieves all accounts for a specific user by ID.
     */
    @GET
    @Path("/all/{user_id}")
    public AccountsAllPayload getAllAccounts(@PathParam("user_id") Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive non-null value.");
        }

        var accountsAllData = accountService.getAllAccounts(userId);

        List<AccountAllPayload> accounts = accountsAllData.getAccounts().stream()
                .map(account -> new AccountAllPayload(
                        account.getId(),
                        account.getUserFirstname(),
                        account.getType(),
                        account.getAmount()
                ))
                .collect(Collectors.toList());

        return new AccountsAllPayload(accounts, accountsAllData.getError());
    }
}
