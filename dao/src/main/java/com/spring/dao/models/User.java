package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for table Users
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;

    /**
     * Constructor method for class {@link User}
     * @param user username of user
     * @param pass password of user
     * @param ac boolean to check if user is active or not
     * @param role role of user
     */
    public User(String user, String pass, boolean ac, String role){
        this.userName = user;
        this.password = pass;
        this.active = ac;
        this.roles = role;
    }

}