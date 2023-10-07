package com.spring.dao.models;

import com.spring.dao.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Entity class for table Tutee
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tutee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutee_id;

    private String firstName;
    private String lastName;
    private String password;
    private boolean active;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Diary diary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutee")
    private Set<TuteeModule> tuteeModules;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "tutee")
    private Set<Feedback> feedbacks;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    /**
     * Constructor method for class {@link Tutee}
     * @param firstName first name of Tutee
     * @param lastName last name of Tutee
     * @param email email of Tutee
     * @param password password of Tutee
     * @param active boolean if the Tutee is active or not
     */
    @Builder
    public Tutee(String firstName, String lastName, String email, String password, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
        this.active=active;
    }

    /**
     * Constructor method for class {@link Tutee} used for security
     * @param s email of Tutee
     * @param s1 password of Tutee
     * @param b boolean if the Tutee is active or not
     */
    public Tutee(String s, String s1, boolean b) {
        this.email =s;
        this.password = s1;
        this.active = b;
    }

    /**
     * Constructor method for class {@link Tutee} without boolean active
     * @param firstName first name of Tutee
     * @param lastName last name of Tutee
     * @param email email of Tutee
     * @param password password of Tutee
     */
    public Tutee(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

}
