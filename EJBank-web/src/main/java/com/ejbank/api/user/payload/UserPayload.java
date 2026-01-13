/**
 * Represents the payload for user information.
 * Contains the first name and last name of the user.
 */
package com.ejbank.api.user.payload;

import java.util.Objects;

public class UserPayload {
    private final String firstname;
    private final String lastname;

    public UserPayload(String firstname, String lastname) {
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
    public String toString() {
        return "{" + "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + '}';
    }
}
