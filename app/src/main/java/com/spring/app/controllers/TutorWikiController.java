package com.spring.app.controllers;

import com.spring.common.interfaces.TutorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling requests sent from the Wiki page for Tutors.
 */
@Controller
public class TutorWikiController {

    /**
     * Handles the request for the tutor/wiki-tutor URI.
     * Retrieves the authenticated user's information, and finds the Tutor object associated with the user's email.
     * Adds attribute "username" with the value username previously set to the model, which is used to write the email
     * of the currently logged user on the webpage.
     * @param model the model object
     * @param request the HttpServletRequest object
     * @return the name of the view "tutor/wiki-tutor"
     */
    @RequestMapping(value = "/tutor/wiki-tutor")
    public String home(Model model, HttpServletRequest request) {
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
        }
        return "tutor/wiki-tutor";
    }
}
