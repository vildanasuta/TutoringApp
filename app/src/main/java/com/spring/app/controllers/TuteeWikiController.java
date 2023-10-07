package com.spring.app.controllers;

import com.spring.common.interfaces.TutorService;
import com.spring.dao.models.Tutor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Controller class for handling requests sent from the Wiki page for tutees.
 */
@Controller
public class TuteeWikiController {
    public final TutorService tutorService;

    /**
     * Create a new instance of {@link TuteeWikiController}
     * @param tutorService service for handling tutors
     */
    public TuteeWikiController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    /**
     * Handles the request for tutee/wiki-tutee URI.
     * Retrieves the authenticated user's information, and finds the Tutee object associated with the user's email.
     * Adds attribute "username" with the value username previously set to the model, which is used to write the email
     * of the currently logged user on the webpage.
     * @param request the HttpServletRequest object
     * @param model the Model object
     * @return the name of the view "tutee/wiki-tutee"
     */
    @RequestMapping(value = "/tutee/wiki-tutee")

    public String home(Model model, HttpServletRequest request) {
        ArrayList<Tutor> tutorArrayList=new ArrayList<>(tutorService.findAll());
        model.addAttribute("tutors", tutorArrayList);
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
        }
        return "tutee/wiki-tutee";
    }

}
