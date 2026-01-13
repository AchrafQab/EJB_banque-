/**
 * Represents the type of a bank account.
 * Includes attributes like the interest rate, overdraft limit, and associated accounts.
 */
package com.ejbank.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ejbank_account_type")
public class EjbankAccountType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name; // Name of the account type

    @Column(name = "rate", nullable = false, precision = 10, scale = 0)
    private BigDecimal rate; // Interest rate for the account type

    @Column(name = "overdraft", nullable = false)
    private Integer overdraft; // Overdraft limit for the account type

    @OneToMany(mappedBy = "accountType", fetch = FetchType.LAZY)
    private List<EjbankAccount> accounts; // List of accounts of this type

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Integer overdraft) {
        this.overdraft = overdraft;
    }

    public List<EjbankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<EjbankAccount> accounts) {
        this.accounts = accounts;
    }
}
