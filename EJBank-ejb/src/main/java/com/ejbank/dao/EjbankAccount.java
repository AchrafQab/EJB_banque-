/**
 * Represents a bank account in the system.
 * Each account belongs to a customer and has an associated account type.
 */
package com.ejbank.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ejbank_account")
public class EjbankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private EjbankCustomer customer; // The customer owning the account

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private EjbankAccountType accountType; // The type of account

    @Column(name = "balance", nullable = false)
    private BigDecimal balance; // The current balance of the account

    public EjbankAccount() {}

    public Integer getId() {
        return id;
    }

    public EjbankCustomer getCustomer() {
        return customer;
    }

    public EjbankAccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setCustomer(EjbankCustomer customer) {
        this.customer = customer;
    }

    public void setAccountType(EjbankAccountType accountType) {
        this.accountType = accountType;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
