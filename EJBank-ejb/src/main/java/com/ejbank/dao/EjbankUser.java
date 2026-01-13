/**
 * Represents a generic user in the banking system.
 * This is the base class for different types of users such as customers and advisors.
 */
package com.ejbank.dao;

import javax.persistence.*;

@Entity
@Table(name = "ejbank_user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class EjbankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true, length = 8)
    private String login; // Unique login for the user

    @Column(name = "password", nullable = false, length = 255)
    private String password; // The password

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email; // Email address of the user

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname; // First name of the user

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname; // Last name of the user

    @Column(name = "type", nullable = false, length = 50)
    private String type; // User type (e.g., customer or advisor)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
