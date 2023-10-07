package com.spring.dao.models;

import com.spring.dao.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Entity class for table Tutor
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private boolean active;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    private Set<Feedback> feedbacks;

    @ManyToOne(cascade = CascadeType.ALL)
    private Module module;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    /**
     * Constructor method for class {@link Tutor}
     * @param id ID of Tutor
     * @param firstName first name of Tutor
     * @param lastName last name of Tutor
     * @param email email of Tutor
     * @param userRole Role of Tutor(Tutor)
     */
    @Builder
    public Tutor(Long id, String firstName, String lastName, String email, UserRole userRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
    }

    /**
     * Constructor method for class {@link Tutor} used for spring security
     * @param s email of Tutor
     * @param s1 password of Tutor
     * @param b boolean to check if Tutor is active or not
     */
    public Tutor(String s, String s1, boolean b) {
        this.email =s;
        this.password = s1;
        this.active = b;
    }

    /**
     * Constructor method for class {@link Tutor} without boolean active
     * @param firstName first name of Tutor
     * @param lastName last name of Tutor
     * @param email email of Tutor
     * @param password password of Tutor
     */
    public Tutor(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }
}