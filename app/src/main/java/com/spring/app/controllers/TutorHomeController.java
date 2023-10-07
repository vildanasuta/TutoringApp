package com.spring.app.controllers;

import com.spring.common.interfaces.TuteeModuleService;
import com.spring.common.interfaces.TuteeService;
import com.spring.common.interfaces.TutorService;
import com.spring.dao.models.Module;
import com.spring.dao.models.Tutee;
import com.spring.dao.models.TuteeModule;
import com.spring.dao.models.Tutor;
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
 * Controller class for handling requests sent from Home page for Tutors.
 */
@Controller
public class TutorHomeController {
    private final TuteeService tuteeService;
    private final TuteeModuleService tuteeModuleService;
    private final TutorService tutorService;
    public List<Tutee> tuteeList=new ArrayList<>();
    public TutorHomeController(TuteeService tuteeService, TuteeModuleService tuteeModuleService, TutorService tutorService) {
        this.tuteeService = tuteeService;
        this.tuteeModuleService = tuteeModuleService;
        this.tutorService = tutorService;
    }

     /**
     * Handles the request for the tutor/tutor-home URI.
     * Retrieves the authenticated user's information, and finds the Tutor object associated with the user's email.
     * Adds attribute "username" with the value username previously set to the model, which is used to write the email
     * of the currently logged user on the webpage.
     * @param request the HttpServletRequest object
     * @param model the Model object
     * @return the name of the view "tutor/home"
     */
    @RequestMapping(value = "tutor/tutor-home")
    public String home(Model model, HttpServletRequest request) {
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
            Optional<Tutor> tutorOptional=tutorService.findByEmail(username);
            Tutor tutor=tutorOptional.get();
            Long moduleId=tutor.getModule().getModule_id();
            List<TuteeModule> tuteeModules= new ArrayList<>(tuteeModuleService.findAll()) ;
            for(TuteeModule tuteeModule:tuteeModules){
                if(tuteeModule.getModule().getModule_id()==moduleId
                && tuteeModule.getEndDate().isAfter(LocalDate.now())){
                    tuteeList.add(tuteeModule.getTutee());
                }
            }
            model.addAttribute("tutees",tuteeList);
        }

        return "tutor/home";
    }
}
