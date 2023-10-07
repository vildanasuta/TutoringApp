package com.spring.app.controllers;
import com.spring.common.interfaces.TuteeModuleService;
import com.spring.common.interfaces.TuteeService;
import com.spring.dao.models.Tutee;
import com.spring.dao.models.TuteeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
/**

 * Controller class for handling requests from the home page for tutees.
 */
@Controller
public class TuteeHomeController {
    private final TuteeModuleService tuteeModuleService;
    private final TuteeService tuteeService;
    Long id;
    Double counter=0.0;
    
     
    public TuteeHomeController(TuteeModuleService tuteeModuleService, TuteeService tuteeService){
        this.tuteeModuleService = tuteeModuleService;
        this.tuteeService = tuteeService;
    }

     /**
     * Handles the request for the tutee/tutee-home URI.
     * Retrieves the authenticated user's information, and finds the Tutee object associated with the user's email.
     * Adds attribute "username" with the value username previously set to the model, which is used to write the email
     * of the currently logged user on the webpage.
     * @param request the HttpServletRequest object
     * @param model the Model object
     * @return the name of the view "tutee/home"
     */
    @RequestMapping(value = "tutee/tutee-home")
    public String home(HttpServletRequest request, Model model) {
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
            Optional<Tutee> tuteeOptional= tuteeService.findByEmail(username);
            id=tuteeOptional.get().getTutee_id();

        }
        System.out.println(id);
        List<TuteeModule> tuteeModules= new ArrayList<>(tuteeModuleService.findAll()) ;
        for(TuteeModule tuteeModule:tuteeModules){
            if(tuteeModule.getTutee().getTutee_id()==id){
                if(tuteeModule.getEndDate().isBefore(LocalDate.now())){
                    counter+=1;
                }
                if(tuteeModule.getEndDate().minusWeeks(1).isBefore(LocalDate.now())){
                    counter+=0.5;
                }

                if(tuteeModule.getEndDate().equals(LocalDate.now().plusDays(1))){
                    model.addAttribute("moduleFeedback",tuteeModule.getModule());
                    switch(tuteeModule.getModule().getName()){
                        case "General":
                            model.addAttribute("feedbackLink", "https://forms.office.com/Pages/ResponsePage.aspx?id=I6QsC7HwHki3MfhO9cSizDJTrBee39FEhOt2X1JJGrNUODE3UFROUk5STlJKRE1NVzhRSzdJMktQUS4u");
                            break;
                        case "DevOps":
                            model.addAttribute("feedbackLink", "https://forms.office.com/pages/designpagev2.aspx?subpage=design&id=I6QsC7HwHki3MfhO9cSizDJTrBee39FEhOt2X1JJGrNUOU9SVkVGSjZCNjBHTVNaUkFBSFRRMkdTVy4u");
                            break;
                        case "Java":
                            model.addAttribute("feedbackLink", "https://forms.office.com/r/HYHSfUFFTU");
                            break;
                        case "QA/TA":
                            model.addAttribute("feedbackLink", "https://forms.office.com/r/GKQBCY2jt7");
                    }
                }


            }
        }
        if(counter!=null){
            Double percentage=counter*25;
            model.addAttribute("counter",percentage);

            System.out.println(percentage);
        }





        return "tutee/home";
    }


}
