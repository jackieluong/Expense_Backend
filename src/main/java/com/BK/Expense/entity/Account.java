package com.BK.Expense.entity;

import com.BK.Expense.enums.RoleEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
public class Account {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "account_seq")
//    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = true)
    private String name;

    @Enumerated(EnumType.STRING)
    //  Prevent duplicate mapping
    private RoleEnum role;

    public Account() {}


    public Account(String email, String password, RoleEnum roleEnum) {
        this.email = email;
        this.password = password;
        this.role = roleEnum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
