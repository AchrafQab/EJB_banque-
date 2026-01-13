/**
 * Represents a customer in the banking system.
 * A customer has accounts and is managed by an advisor.
 */
package com.ejbank.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ejbank_customer")
@DiscriminatorValue(value = "customer")
public class EjbankCustomer extends EjbankUser implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id", nullable = false)
    private EjbankAdvisor advisor; // Advisor assigned to the customer

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<EjbankAccount> accounts; // Accounts owned by the customer

    public EjbankCustomer() {}

    public EjbankAdvisor getAdvisor() {
        return advisor;
    }

    public Set<EjbankAccount> getAccounts() {
        return accounts;
    }
}
