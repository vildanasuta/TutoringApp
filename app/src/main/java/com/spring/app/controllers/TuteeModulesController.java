package com.spring.app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling requests sent from the Modules page for tutees.
 */
@Controller
public class TuteeModulesController {
    /**
     * Handles the request for tutee/modules-tutee URI.
     * Retrieves the authenticated user's information, and finds the Tutee object associated with the user's email.
     * Adds attribute "username" with the value username previously set to the model, which is used to write the email
     * of the currently logged user on the webpage.
     * @param request the HttpServletRequest object
     * @param model the Model object
     * @return the name of the view "tutee/modules-tutee"
     */
    @RequestMapping(value = "/tutee/modules-tutee")
    public String home(HttpServletRequest request, Model model) {
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
        }
        return "tutee/modules-tutee";
    }
}
