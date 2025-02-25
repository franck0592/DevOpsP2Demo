package com.revature.P1DemoBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Column(name = "userId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    @Column(name="userName", nullable = false)
    private String username;
    @Column(name = "passWord")
    private String password;
    private String role="user";
    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reimbursement> reimbursements;

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String username, String password, String role, List<Reimbursement> reimbursements) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.reimbursements = reimbursements;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Reimbursement> getReimbursements() {
        return reimbursements;
    }

    public void setReimbursements(List<Reimbursement> reimbursements) {
        this.reimbursements = reimbursements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(reimbursements, user.reimbursements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, username, password, role, reimbursements);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", passWord='" + password + '\'' +
                ", role='" + role + '\'' +
                ", reimbursements=" + reimbursements +
                '}';
    }
}
