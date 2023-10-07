package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class for table Answer
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;

    private int grade;

    private String comment;


    @ManyToOne
    private Question question;

    @ManyToOne
    private Feedback feedback;
}
