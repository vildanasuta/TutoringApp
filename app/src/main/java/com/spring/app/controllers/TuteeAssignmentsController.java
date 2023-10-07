package com.spring.app.controllers;

import com.spring.common.interfaces.AssignmentService;
import com.spring.common.interfaces.TuteeModuleService;
import com.spring.common.interfaces.TuteeService;
import com.spring.dao.models.Assignment;
import com.spring.dao.models.Tutee;
import com.spring.dao.models.TuteeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class handling requests sent from the assignments page for tutees.
 */
@Controller
public class TuteeAssignmentsController {
    private final AssignmentService assignmentService;
    private final TuteeModuleService tuteeModuleService;
    private final TuteeService tuteeService;

    private Long moduleId;
    private Tutee tutee;

    /**
     * Creates a new instance of {@link TuteeAssignmentsController}
     * @param assignmentService service for handling assignments.
     * @param tuteeModuleService service for handling tutee - module relation.
     * @param tuteeService service for handling tutees.
     */
    public TuteeAssignmentsController(AssignmentService assignmentService, TuteeModuleService tuteeModuleService, TuteeService tuteeService) {
        this.assignmentService = assignmentService;
        this.tuteeModuleService = tuteeModuleService;
        this.tuteeService = tuteeService;
    }

    /**

     * Handles the requests to the /tutee/assignments-tutee URI.
     * Retrieves the currently authenticated user's username and adds it to the model.
     * Retrieves the Tutee object associated with the authenticated user's email from the TuteeService.
     * Retrieves all TuteeModule objects associated with the Tutee object and finds the module_id of the module
     * that has end date after current date.
     * Retrieves all Assignment objects from the AssignmentService.
     * Adds all Assignment objects that match the module_id obtained earlier to a new list and adds it to the model.
     * Returns the name of the view "tutee/assignments-tutee".
     * @param request the HttpServletRequest object
     * @param model the model object
     * @return the name of the view "tutee/assignments-tutee"
     */
    @RequestMapping(value = "/tutee/assignments-tutee")
    public String home(HttpServletRequest request, Model model) {
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
            Optional<Tutee> tuteeOptional= tuteeService.findByEmail(username);
            if (tuteeOptional.isPresent()) {
                tutee= tuteeOptional.get();
            } else {
                // handle the case where tutee is not present
            }
        }

        List<TuteeModule> tuteeModules = tuteeModuleService.findByTutee(tutee);
        for(TuteeModule tuteeModule: tuteeModules){
                if(tuteeModule.getEndDate().isAfter(LocalDate.now())){
                    moduleId = tuteeModule.getModule().getModule_id();
                }
        }


        List<Assignment> assignmentList=  new ArrayList<>(assignmentService.findAll());
        List<Assignment> assignmentsForTutee = new ArrayList<>();
        for(Assignment assignment: assignmentList){
            if(assignment.getModuleId()==moduleId){
                assignmentsForTutee.add(assignment);
                System.out.println(assignment.getName());
            }
        }


        if(!assignmentsForTutee.isEmpty()){
            model.addAttribute("assignmentsForTutee",assignmentsForTutee);
        }

        return "tutee/assignments-tutee";
    }
}
