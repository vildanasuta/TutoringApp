package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class for custom connecting table TuteeModule
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TuteeModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tutee tutee;

    @ManyToOne
    private Module module;

    private LocalDate startDate;

    private LocalDate endDate;

    /**
     * Constructor method for class {@link TuteeModule}
     * @param id ID of TuteeModule
     * @param tutee Tutee that is connected to the Module
     * @param module Module that is connected to the Tutee
     * @param startDate Starting date of Tutee being on this Module
     * @param endDate Ending date of Tutee being on this Module
     */
    @Builder
    public TuteeModule(Long id, Tutee tutee, Module module, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.tutee = tutee;
        this.module = module;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}