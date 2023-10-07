package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class for table Question
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "question")
    private Set<Answer> answers;

    /**
     * Constructor method for class {@link Question}
     * @param id ID of question
     * @param question text of question
     */
    @Builder
    public Question(Long id, String question){
        this.id = id;
        this.question = question;
    }

    public Long getId() {
        return id;
    }
}
