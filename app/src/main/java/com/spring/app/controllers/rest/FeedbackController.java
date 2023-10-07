package com.spring.app.controllers.rest;

import com.spring.common.services.FeedbackServiceImpl;
import com.spring.dao.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackServiceImpl feedbackService;

    @GetMapping("/")
    private Set<Feedback> getAllFeedbacks(){
        return feedbackService.findAll();
    }

    @GetMapping("/{id}")
    private Feedback getFeedback(@PathVariable("id") Long id){
        return feedbackService.findById(id);
    }

    @PostMapping("/")
    private Long addFeedback(@RequestBody Feedback feedback){
        feedbackService.save(feedback);
        return feedback.getId();
    }

    @PutMapping("/")
    private Feedback updateFeedback(@RequestBody Feedback feedback){
        feedbackService.save(feedback);
        return feedback;
    }

    @DeleteMapping("/{id}")
    private void deleteFeeback(@PathVariable("id") Long id){
        feedbackService.deleteById(id);
    }
}
