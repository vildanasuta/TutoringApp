package com.spring.app.config;


import com.spring.dao.models.Diary;
import com.spring.dao.models.Tutee;
import com.spring.dao.models.Tutor;
import com.spring.dao.models.User;
import com.spring.dao.repositories.DiaryRepository;
import com.spring.dao.repositories.TuteeRepository;
import com.spring.dao.repositories.TutorRepository;
import com.spring.dao.repositories.UserRepository;

import com.spring.dao.models.*;
import com.spring.dao.models.Module;
import com.spring.dao.repositories.*;

import com.sun.tools.javac.Main;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
public class Bootstrap implements CommandLineRunner {

    static Logger logger = Logger.getLogger(Main.class.getName());
    @Autowired
    UserRepository userRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    TuteeRepository tuteeRepository;

    @Autowired

    private DiaryRepository diaryRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    TaskRepository taskRepository;


    @Autowired
    AssignmentRepository assignmentRepository;



    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User(1,
                "admin",
                "admin",
                true,
                "ADMIN"));
        logger.info("da li se vidi");

        Stream.iterate(1, i->i+1)
                .skip(1)
                .limit(10)
                .map(e -> new Tutee("Ime","Prezime","tutee"+e,"pass"+e,true))
                .forEach(tuteeRepository::save);

        Stream.iterate(1, i->i+1)
                .skip(1)
                .limit(10)
                .map(e -> new Tutor("tutor"+e,"pass"+e,true))
                .forEach(tutorRepository::save);
        

        // Create some diary entries and save them to the database
        Stream.iterate(1, i -> i + 1)
                .skip(1)
                .limit(10)
                .map(i -> new Diary(new Date(), "Title " + i, "Texthsauidhuiafhguiahufiah" +
                        " guiwahuil" +
                        "d hawuilhduilah wuilaghuiw dau g" +
                        "uw dasgjhkbaui ajsbdyawhv;alyhgcfvw cfwgjfabnmfawbhj,  " + i))
                .forEach(diaryRepository::save);

        // Retrieve the saved diary entries from the database and print them to the console
        List<Diary> diaryList = diaryRepository.findAll();
        System.out.println(diaryList);

        moduleRepository.save(new Module
                (1L,"Java","The objectives of this module are: " +
                        "Establish proficiency to work on enterprise projects, " +
                        "understands basics of working with Java, Spring Boot, " +
                        "Knows how to write proper SML tests and proper error handling"));

        taskRepository.save(new Task(1L,"Investigate exceptions in Java.","" +
                "What they are?\n" +
                "What kinds of exceptions exist?\n" +
                "How are they handled?\n" +
                "Reserved words: try-catch, throw, and throws"));

        taskRepository.save(new Task(2L,"Look into Junit tests.","Annotations\n" +
                "Assertions (fails)\n" +
                "Code coverage\n" +
                "Junit 4 vs 5\n" +
                "Mockito, PowerMock, EasyMock"));

        //logger.info((Supplier<String>) tuteeRepository.findAll());
        //logger.info((Supplier<String>) userRepository.findAll());

        //logger.info("............................................................");
        //logger.info((Supplier<String>) assignmentRepository.findAll());

        //TODO Koristiti logove


    }
}