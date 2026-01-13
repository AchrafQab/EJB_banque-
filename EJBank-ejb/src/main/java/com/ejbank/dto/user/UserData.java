/**
 * Represents basic user information with first and last name details.
 * This class is immutable and ensures the user's first name and last name are non-null.
 */
package com.ejbank.dto.user;

import java.util.Objects;

public class UserData {

    private final String firstname; // The user's first name
    private final String lastname;  // the user's last name

    public UserData(String firstname, String lastname) {
        this.firstname = Objects.requireNonNull(firstname, "Firstname cannot be null");
        this.lastname = Objects.requireNonNull(lastname, "Lastname cannot be null");
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return firstname.equals(userData.firstname) && lastname.equals(userData.lastname);
    }

    @Override
    public String toString() {
        return "{" + "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + '}';
    }
}
