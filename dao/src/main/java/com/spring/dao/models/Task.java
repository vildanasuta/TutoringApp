package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class of table Task
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Assignment assignment;

    /**
     * Constructor method of class {@link Task}
     * @param id ID of task
     * @param name name of task
     * @param description description of task
     */
    @Builder
    public Task(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}