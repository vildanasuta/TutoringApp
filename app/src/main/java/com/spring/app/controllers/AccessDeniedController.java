package com.spring.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling access denied requests.
 */
@Controller
public class AccessDeniedController {

    /**
     * Method for handling the access denied request.
     * The request is sent when a user that does not have specific privileges tries to access a certain page.
     * @return the string "tutor/access-denied" (send to specific html page)
     */
    @RequestMapping(value = "/access-denied")
    public String home() {
        return "tutor/access-denied";
    }
}

