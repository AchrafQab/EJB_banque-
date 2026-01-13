/**
 * API for managing transactions.
 * Provides endpoints for listing transactions, previewing a transaction, applying a transaction, and validating a transaction.
 */
package com.ejbank.api.transaction;

import com.ejbank.api.transaction.payload.*;
import com.ejbank.dto.transaction.*;
import com.ejbank.service.transaction.TransactionService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransactionApi {

    @EJB
    private TransactionService transactionService;

    /**
     * Retrieves a list of transactions for a specific account and user.
     */
    @GET
    @Path("/list/{account_id}/{offset}/{user_id}")
    public TransactionListData getTransactions(@PathParam("account_id") int accountId, @PathParam("offset") int offset, @PathParam("user_id") int userId) {
        return transactionService.getTransactions(accountId, offset, userId);
    }

    /**
     * Previews a transaction and provides details on its feasibility.
     */
    @POST
    @Path("/preview")
    @Consumes(MediaType.APPLICATION_JSON)
    public TransactionPreviewResponsePayload previewTransaction(TransactionPreviewRequestPayload request) {

        if (request.getSource() <= 0 || request.getDestination() <= 0 ||
                request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0 ||
                request.getAuthor() <= 0) {
            return new TransactionPreviewResponsePayload(
                    false, null, null, "Invalid input parameters.", null
            );
        }

        var transactionPreviewData = transactionService.previewTransaction(
                request.getSource(),
                request.getDestination(),
                request.getAmount(),
                request.getAuthor()
        );

        return new TransactionPreviewResponsePayload(
                transactionPreviewData.isResult(),
                transactionPreviewData.getBefore(),
                transactionPreviewData.getAfter(),
                transactionPreviewData.getMessage(),
                transactionPreviewData.getError()
        );
    }

    /**
     * Applies a transaction and updates account balances if successful.
     */
    @POST
    @Path("/apply")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionApplyResponsePayload applyTransaction(TransactionApplyRequestPayload request) {
        if (request.getSource() <= 0 || request.getDestination() <= 0 ||
                request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0 ||
                request.getAuthor() <= 0) {
            return new TransactionApplyResponsePayload(false, "Invalid input parameters.");
        }

        var transactionApplyData = transactionService.applyTransaction(
                request.getSource(),
                request.getDestination(),
                request.getAmount(),
                request.getComment(),
                request.getAuthor()
        );

        return new TransactionApplyResponsePayload(transactionApplyData.isResult(), transactionApplyData.getMessage());
    }

    /**
     * Validates a transaction (approves or rejects it).
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validation")
    public TransactionValidationResponsePayload validateTransaction(TransactionValidationRequestPayload request) {

        var validationData = transactionService.validateTransaction(
                request.getTransaction(),
                request.isApprove(),
                request.getAuthor()
        );

        return new TransactionValidationResponsePayload(
                validationData.isResult(),
                validationData.getMessage(),
                validationData.getError()
        );
    }

    /**
     * Retrieves the count of pending transactions requiring validation for a specific advisor.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/validation/notification/{user_id}")
    public int getPendingTransactionNotifications(@PathParam("user_id") int userId) {
        return transactionService.countPendingTransactionsForAdvisor(userId).intValue();
    }
}
