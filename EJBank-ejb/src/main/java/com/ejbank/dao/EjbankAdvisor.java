/**
 * Represents a bank advisor in the system.
 * An advisor is responsible for managing multiple customers.
 */
package com.ejbank.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue(value = "advisor")
public class EjbankAdvisor extends EjbankUser implements Serializable {

    @OneToMany(mappedBy = "advisor", fetch = FetchType.LAZY)
    private Set<EjbankCustomer> customers; // Customers managed by the advisor

    public EjbankAdvisor() {}

    public Set<EjbankCustomer> getCustomers() {
        return customers;
    }
}
