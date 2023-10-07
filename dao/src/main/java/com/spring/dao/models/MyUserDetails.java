package com.spring.dao.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class used for spring security - adding users to specific roles and permissions
 */
public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    /**
     * Constructor method for adding users to database with admin privileges
     * @param user instance of class {@link User}
     */
    public MyUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Constructor method for adding tutors to database with tutor privileges
     * @param tutor instance of class {@link Tutor}
     */
    public MyUserDetails(Tutor tutor) {
        List<String> list = new ArrayList<>();
        list.add("TUTOR");
        this.userName = tutor.getEmail();
        this.password = tutor.getPassword();
        this.active = tutor.isActive();
        this.authorities = list.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Constructor method for adding tutees to database with tutee privileges
     * @param tutee
     */
    public MyUserDetails(Tutee tutee) {
        List<String> list = new ArrayList<>();
        list.add("TUTEE");
        this.userName = tutee.getEmail();
        this.password = tutee.getPassword();
        this.active = tutee.isActive();
        this.authorities = list.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    /**
     * Method for returning authority of user
     * @return list of {@link GrantedAuthority}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Getter method for user password
     * @return password of user
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Getter method for user username
     * @return username of user
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Method to check if account is expired or not
     * @return boolean value true(not expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Method to check if account is  locked or not
     * @return boolean value true(not locked)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Method to check if account credentials are expired or not
     * @return boolean value true(not expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Method to check if account is enabled
     * @return boolean value {@link #active}
     */
    @Override
    public boolean isEnabled() {
        return active;
    }
}
