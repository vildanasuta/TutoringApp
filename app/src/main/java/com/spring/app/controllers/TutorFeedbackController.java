package com.spring.app.controllers;

import com.spring.common.interfaces.*;
import com.spring.dao.models.*;
import com.spring.dao.models.Module;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Controller class for handling requests sent from Feedback page for Tutors.
 */
@Controller
public class TutorFeedbackController {

    public final QuestionService questionService;
    public final TuteeService tuteeService;
    public final TutorService tutorService;
    public final ModuleService moduleService;
    public final AnswerService answerService;
    public final FeedbackService feedbackService;

    /**
     * Create a new instance of {@link TutorFeedbackController}
     * @param questionService service to handle questions
     * @param tuteeService service to handle tutees
     * @param tutorService service to handle tutors
     * @param moduleService service to handle modules
     * @param answerService service to handle answers
     * @param feedbackService service to handle feedbacks
     */
    public TutorFeedbackController(QuestionService questionService, TuteeService tuteeService, TutorService tutorService, ModuleService moduleService, AnswerService answerService, FeedbackService feedbackService) {
        this.questionService = questionService;
        this.tuteeService = tuteeService;
        this.tutorService = tutorService;
        this.moduleService = moduleService;
        this.answerService = answerService;
        this.feedbackService = feedbackService;
    }

    /**
     * Handles the request to the /tutor/feedback-tutor URI.
     * Retrieves a list of all Tutee objects from the TuteeService and adds it to the model.
     * Retrieves a list of all Tutor objects from the TutorService and adds it to the model.
     * Retrieves a list of all Module objects from the ModuleService and adds it to the model.
     * Retrieves a list of all Question objects from the QuestionService and adds it to the model.
     * Retrieves the currently authenticated user's username and adds it to the model.
     * @param request the HttpServletRequest object
     * @param model the model object
     * @return the name of the view "tutor/feedback-tutor"
     */
    @RequestMapping(value = "/tutor/feedback-tutor")
    public String home(HttpServletRequest request, Model model) {
        List<Tutee> tuteeList = new ArrayList<>(tuteeService.findAll());
        model.addAttribute("tutees",tuteeList);
        List<Tutor> tutorList = new ArrayList<>(tutorService.findAll());
        model.addAttribute("tutors",tutorList);
        List<Module> moduleList = new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleList);
        List<Question> questionList = new ArrayList<>(questionService.findAll());
        Collections.sort(questionList, Comparator.comparing(Question::getId));
        model.addAttribute("questions",questionList);
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
        }
        return "tutor/feedback-tutor";
    }

    /**
     * Adds feedback to the system.
     * @param tutee the ID of the tutee to which the feedback belongs
     * @param tutor the ID of the tutor who gave the feedback
     * @param module the ID of the module associated with the feedback
     * @param suggestedStep the suggested next step for the tutee
     * @param grades list of grades given to the questions
     */
    @PostMapping("/tutor/feedback-tutor")
    public void addFeedback(@RequestParam Long tutee, @RequestParam Long tutor,
                            @RequestParam Long module,@RequestParam String suggestedStep,@RequestParam List<Integer> grades, Model model){

        Feedback feedback = new Feedback();
        feedback.setTutor(tutorService.findById(tutor));
        feedback.setTutee(tuteeService.findById(tutee));
        feedback.setModule(moduleService.findById(module));
        feedback.setNextStep(suggestedStep);
        feedbackService.save(feedback);
        int i = 0;
        List<Question> questions = new ArrayList<>(questionService.findAll());
        Collections.sort(questions, Comparator.comparing(Question::getId));
        for (Question q:questions){

            Answer answer = new Answer();
            answer.setFeedback(feedback);
            answer.setQuestion(q);
            answer.setGrade(grades.get(i));
            i++;


            answerService.save(answer);
        }
        List<Tutee> tuteeList = new ArrayList<>(tuteeService.findAll());
        model.addAttribute("tutees",tuteeList);
        List<Tutor> tutorList = new ArrayList<>(tutorService.findAll());
        model.addAttribute("tutors",tutorList);
        List<Module> moduleList = new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleList);
        List<Question> questionList = new ArrayList<>(questionService.findAll());
        Collections.sort(questionList, Comparator.comparing(Question::getId));
        model.addAttribute("questions",questionList);

    }
    }