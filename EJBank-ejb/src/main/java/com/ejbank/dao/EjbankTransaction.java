/**
 * Represents a financial transaction in the banking system.
 * A transaction involves a source account, a destination account,
 * an author (user who initiated the transaction), and details like amount, comment, and date.
 */
package com.ejbank.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ejbank_transaction")
public class EjbankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "account_id_from", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EjbankAccount accountFrom; // Source account for the transaction

    @JoinColumn(name = "account_id_to", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EjbankAccount accountTo; // Destination account for the transaction

    @JoinColumn(name = "author", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EjbankUser author; // User who initiated the transaction

    @Column(name = "amount", nullable = false)
    private BigDecimal amount; // Transaction amount

    @Column(name = "comment", nullable = false)
    private String comment; // Comment of the transaction

    @Column(name = "applied", nullable = false)
    private Boolean applied; // Indicates whether the transaction is applied or not

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date; // Date when the transaction was created

    // Constructor with parameters
    public EjbankTransaction(EjbankAccount accountFrom, EjbankAccount accountTo, EjbankUser author, BigDecimal amount, String comment, Boolean applied, Date date) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.author = author;
        this.amount = amount;
        this.comment = comment;
        this.applied = applied;
        this.date = date;
    }

    // Default constructor
    public EjbankTransaction() {}

    public Integer getId() {
        return id;
    }

    public EjbankAccount getAccountFrom() {
        return accountFrom;
    }

    public EjbankAccount getAccountTo() {
        return accountTo;
    }

    public EjbankUser getAuthor() {
        return author;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public Date getDate() {
        return date;
    }
}
