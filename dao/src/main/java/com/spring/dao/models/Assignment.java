package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class for table Assignment
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Module module;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "assignment")
    private Set<Task> tasks;

    /**
     * Constructor method for {@link Assignment}
     * @param id id of assignment
     * @param name name of the assignment
     * @param description description of the assignment
     */
    @Builder
    public Assignment (Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Getter method for module ID to bypass the " _ " error in getModule_id() method from class {@link Module}
     * @return ID of module assigned to the assignment
     */
    public long getModuleId() {
       return this.module.getModule_id();
    }
}