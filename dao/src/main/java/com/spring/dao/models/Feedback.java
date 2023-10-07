package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class for table Feedback
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nextStep;
    private String additionalComment;

    @ManyToOne
    private Module module;
    @ManyToOne
    private Tutor tutor;
    @ManyToOne
    private Tutee tutee;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "feedback")
    private Set<Answer> answers;

    /**
     * Constructor method for class {@link Feedback}
     * @param id ID of feedback
     * @param nextStep next step for tutee to take after being evaluated
     * @param additionalComment additional comment for the tutee
     */
    @Builder
    public Feedback(Long id, String nextStep, String additionalComment) {
        this.id = id;
        this.nextStep = nextStep;
        this.additionalComment = additionalComment;
    }
}