/**
 * Represents details of a single transaction, including its source, destination,
 * amount, author, and current state.
 */
package com.ejbank.dto.transaction;

import java.math.BigDecimal;

public class TransactionData {
    private final Long id; // Transaction iD
    private final String date; // Date of the transaction
    private final String source; // Source account description
    private final String destination; // Destination account description
    private final String destinationUser; // User associated with the destination account
    private final BigDecimal amount; // Transaction amount
    private final String author; // Name of the user who initiated the transaction
    private final String comment; // Additional comments about the transaction
    private final String state; // Current state of the transaction (for ex:, "WAINTING_TO_APPROVE", "APPLIED")

    public TransactionData(Long id, String date, String source, String destination, String destinationUser,
                           BigDecimal amount, String author, String comment, String state) {
        this.id = id;
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.destinationUser = destinationUser;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAuthor() {
        return author;
    }

    public String getComment() {
        return comment;
    }

    public String getState() {
        return state;
    }
}