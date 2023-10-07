package com.spring.dao.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entity class for table Diary
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Diary {
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Lob
    private String text;

    @OneToOne
    private Tutee tutee;

    /**
     * Constructor method for class {@link Diary}
     * @param id ID of diary
     * @param date Date the diary was created/edited
     * @param title title of the diary
     * @param text text inside the diary
     */
    @Builder
    public Diary(Long id, Date date, String title, String text) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.text = text;
    }

    /**
     * Constructor method for class {@link Diary}
     * @param date Date the diary was created/edited
     * @param title title of the diary
     * @param text text inside the diary
     */
    public Diary(Date date, String title, String text) {
        this.date = date;
        this.title = title;
        this.text = text;
    }

    /**
     * Constructor method for class {@link Diary} with starting date
     * @param date Date the diary was created
     * @param title title of the diary
     * @param text text inside the diary
     */
    public Diary(String date, String title, String text) {

        Date date1 = new SimpleDateFormat(date).get2DigitYearStart();
        this.date = date1;
        this.title = title;
        this.text = text;
    }

    /**
     * Constructor method for class {@link Diary} with initialized tutor
     * @param t Instance of class {@link Tutee}
     */
    public Diary(Tutee t){
        this.date = new Date();
        this.title = "my diary";
        this.text = "";
        this.tutee = t;

    }

}

