/**
 * API endpoint for managing account-related operations.
 * Provides functionality to retrieve account details.
 */
package com.ejbank.api.account;

import com.ejbank.api.account.payload.AccountDetailsPayload;
import com.ejbank.service.account.AccountService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountApi {

    @EJB
    private AccountService accountService;

    /**
     * Retrieves the details of a specific account for a given user.
     */
    @GET
    @Path("/{account_id}/{user_id}")
    public AccountDetailsPayload getAccountDetails(@PathParam("account_id") Integer accountId, @PathParam("user_id") Integer userId) {
        if (accountId == null || accountId <= 0 || userId == null || userId <= 0) {
            throw new IllegalArgumentException("Account ID and User ID must be positive non-null values.");
        }

        var accountDetailsData = accountService.getAccountDetails(accountId, userId);

        if (accountDetailsData.getError() != null) {
            return new AccountDetailsPayload(null, null, null, null, null, accountDetailsData.getError());
        }

        return new AccountDetailsPayload(
                accountDetailsData.getOwner(),
                accountDetailsData.getAdvisor(),
                accountDetailsData.getRate(),
                accountDetailsData.getInterest(),
                accountDetailsData.getAmount(),
                null
        );
    }
}
