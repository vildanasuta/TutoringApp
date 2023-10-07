package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class for table Module
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long module_id;

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private Set<TuteeModule> tuteeModules;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private Set<Tutor> tutors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private Set<Assignment> assignments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private Set<Feedback> feedbacks;

    /**
     * Constructor method for class {@link Module}
     * @param module_id ID of module
     * @param name name of module
     * @param description description of module
     */
    @Builder
    public Module(Long module_id, String name, String description) {
        this.module_id = module_id;
        this.name = name;
        this.description = description;
    }

}