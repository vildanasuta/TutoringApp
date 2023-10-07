package com.spring.app.controllers.rest;

import com.spring.common.services.AssignmentServiceImpl;
import com.spring.dao.models.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/assignments")
public class AssignmentController {

    @Autowired
    AssignmentServiceImpl assignmentService;

    @GetMapping("/")
    private Set<Assignment> getAllAssignments(){
        return assignmentService.findAll();
    }

    @GetMapping("/{id}")
    private Assignment getAssignment(@PathVariable("id") Long id){
        return assignmentService.findById(id);
    }

    @PostMapping("/")
    private Long addAssignment(@RequestBody Assignment assignment){
        assignmentService.save(assignment);
        return assignment.getId();
    }

    @PutMapping("/")
    private Assignment updateAssignment(@RequestBody Assignment assignment){
        assignmentService.save(assignment);
        return assignment;
    }

    @DeleteMapping("/{id}")
    private void deleteAssignment(@PathVariable("id") Long id){
        assignmentService.deleteById(id);
    }
}
