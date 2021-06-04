package com.dubroushchyk.first_spring.entity;

import com.dubroushchyk.first_spring.enums.EnumRole;
import com.dubroushchyk.first_spring.enums.EnumStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table (name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(name = "user_name", unique = true)
    private String userName;

//    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column (name = "password")
    private String password;

    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column (name = "first_name")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "aaaaaaaa")
    @Column (name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column (name = "role")
    private EnumRole role;

    @Enumerated(EnumType.STRING)
    @Column (name = "status")
    private EnumStatus status;

    @CreationTimestamp
    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserAccount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
