package com.spring.app.controllers.rest;

import com.spring.common.services.TutorServiceImpl;
import com.spring.dao.models.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/tutors")
public class TutorController {

    @Autowired
    TutorServiceImpl tutorService;

    @GetMapping("/")
    private Set<Tutor> getAllTutors(){
        return tutorService.findAll();
    }

    @GetMapping("/{id}")
    private Tutor getTutor(@PathVariable("id") Long id){
        return tutorService.findById(id);
    }

    @PostMapping("/")
    private Long addTutor(@RequestBody Tutor tutor){
        tutorService.save(tutor);
        return tutor.getId();
    }

    @PutMapping("/")
    private Tutor updateTutor(@RequestBody Tutor tutor){
        tutorService.save(tutor);
        return tutor;
    }

    @DeleteMapping("/{id}")
    private void deleteTutor(@PathVariable("id") Long id){
        tutorService.deleteById(id);
    }
}
