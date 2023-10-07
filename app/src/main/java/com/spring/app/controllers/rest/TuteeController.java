package com.spring.app.controllers.rest;

import com.mysql.cj.log.Log;
import com.spring.common.services.TuteeServiceImpl;
import com.spring.dao.models.Tutee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/tutees")
public class TuteeController {

    @Autowired
    TuteeServiceImpl tuteeService;

    @GetMapping("/")
    private Set<Tutee> getAllTutees(){
        return tuteeService.findAll();
    }

    @GetMapping("/{id}")
    private Tutee getTutee(@PathVariable("id") Long id){
        return tuteeService.findById(id);
    }

    @PostMapping("/")
    private Long addTutee(@RequestBody Tutee tutee){
        tuteeService.save(tutee);
        return tutee.getTutee_id();
    }

    @PutMapping("/")
    private Tutee updateTutee(@RequestBody Tutee tutee){
        tuteeService.save(tutee);
        return tutee;
    }

    @DeleteMapping("/{id}")
    private void deleteTutee(@PathVariable("id") Long id){
        tuteeService.deleteById(id);
    }
}
