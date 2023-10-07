package com.spring.app.controllers;


import com.spring.common.interfaces.*;
import com.spring.common.services.MyUserDetailsService;
import com.spring.dao.models.*;

import com.spring.dao.models.Module;
import com.spring.dao.repositories.TuteeModuleRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class handling request sent by a user with admin privileges on pages specific for admin.
 */
@Controller
public class AdminController {

    private final TutorService tutorService;
    private final TuteeService tuteeService;
    private final MyUserDetailsService myUserDetailsService;
    private final DiaryService diaryService;
    private final ModuleService moduleService;
    private final TuteeModuleService tuteeModuleService;

    /**
     * Creates a new instance of {@link AdminController}
     * @param tutorService service for handling tutors
     * @param tuteeService service for handling tutees
     * @param myUserDetailsService spring security integrated Service for handling users(user roles/privileges)
     * @param diaryService  service for handling diaries
     * @param moduleService service for handling modules
     * @param tuteeModuleService service for handling tutee - module relation
     */
    public AdminController(TutorService tutorService, TuteeService tuteeService, MyUserDetailsService myUserDetailsService, DiaryService diaryService, ModuleService moduleService,
                           TuteeModuleService tuteeModuleService) {
        this.tuteeService = tuteeService;
        this.myUserDetailsService = myUserDetailsService;
        this.tutorService = tutorService;
        this.diaryService = diaryService;
        this.moduleService = moduleService;
        this.tuteeModuleService = tuteeModuleService;
    }

    /**
     * Handles the form submission for creating a new user.
     * The role of the user is determined by the value of the "role" request parameter.
     * If the role is "tutor", a new Tutor object is created and saved.
     * If the role is "tutee", a new Tutee object is created, a Diary object is associated with it and saved,
     * and a TuteeModule object is created and saved. A User object is also created and saved.
     * The user's first name, last name, email and password are passed as request parameters.
     * After creating the user, the method returns a ModelAndView object that redirects to the admin panel.
     * @param role string - role of new user.(Tutor or Tutee)
     * @param firstname string - first name of new user.
     * @param lastname string - last name of new user.
     * @param email string - email of new user.
     * @param password string - password of new user.
     * @param model model object
     * @return
     */
    @PostMapping(value="/admin/admin-panel")
    public ModelAndView formHandler(@RequestParam String role,
                                    @RequestParam String firstname,
                                    @RequestParam String lastname,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    @RequestParam Long module_id,
                                    Model model){

        User user = new User();
        if (role.equals("tutor")){
            Tutor tutor = new Tutor();
            tutor.setFirstName(firstname);
            tutor.setLastName(lastname);
            tutor.setEmail(email);
            tutor.setPassword(password);
            Module selectedModule = moduleService.findById(module_id);
            tutor.setModule(selectedModule);
            tutor.setActive(true);
            user.setUserName(tutor.getEmail());
            user.setPassword(tutor.getPassword());
            user.setActive(true);
            user.setRoles("TUTOR");
            tutorService.save(tutor);
            myUserDetailsService.save(user);
        }
        else{
            Tutee tutee = new Tutee();
            tutee.setFirstName(firstname);
            tutee.setLastName(lastname);
            tutee.setEmail(email);
            tutee.setPassword(password);
            tutee.setActive(true);
            user.setUserName(tutee.getEmail());
            user.setPassword(tutee.getPassword());
            user.setActive(true);
            user.setRoles("TUTEE");
            Diary d = new Diary(tutee);
            tutee.setDiary(d);
            TuteeModule tm=new TuteeModule();
            tm.setTutee(tutee);
            tm.setModule(moduleService.findById(1L));
            tm.setStartDate(LocalDate.now());
            tm.setEndDate(LocalDate.now().plusWeeks(2));
            tuteeService.save(tutee);
            myUserDetailsService.save(user);
            diaryService.save(d);
            tuteeModuleService.save(tm);
        }



        List tutorArrayList=new ArrayList<>(tutorService.findAll());
        model.addAttribute("tutors", tutorArrayList);
        List tuteesArrayList=new ArrayList<>(tuteeService.findAll());
        model.addAttribute("tutees", tuteesArrayList);
        List moduleArrayList=new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleArrayList);
        List tuteeModuleArrayList=new ArrayList<>(tuteeModuleService.findAll());
        model.addAttribute("tuteeModules",tuteeModuleArrayList);
        return new ModelAndView("redirect:/admin/admin-panel");
    }
    /**
     * Handles the form submission for creating a new TuteeModule.
     * Retrieves the Tutee object with the specified ID from the TuteeService and the
     * Module object with the specified ID from the ModuleService.
     * Associates the Tutee and Module objects with a new TuteeModule object, sets the start and end date,
     * and saves the TuteeModule object using the TuteeModuleService.
     * Retrieves all Tutor, Tutee, Module, and TuteeModule objects from the corresponding services and adds them to the model.
     * Returns a ModelAndView object that redirects to the admin/dashboard URI.
     * @param tutee_id the ID of the Tutee for the TuteeModule
     * @param module_id the ID of the Module for the TuteeModule
     * @param startDate the start date for the TuteeModule
     * @param endDate the end date for the TuteeModule
     * @param model the model object
     * @return a ModelAndView object that redirects to the admin/dashboard URI
     */
    @PostMapping(value="/admin/dashboard")
    public ModelAndView form(@RequestParam Long tutee_id,
                             @RequestParam Long module_id,
                             @RequestParam LocalDate startDate,
                             @RequestParam LocalDate endDate,
                             Model model){
        TuteeModule tuteeModule=new TuteeModule();
        Tutee objectTutee=tuteeService.findById(tutee_id);
        tuteeModule.setTutee(objectTutee);
        Module objectModule=moduleService.findById(module_id);
        tuteeModule.setModule(objectModule);
        tuteeModule.setStartDate(startDate);
        tuteeModule.setEndDate(endDate);
        tuteeModuleService.save(tuteeModule);

        List tutorArrayList=new ArrayList<>(tutorService.findAll());
        model.addAttribute("tutors", tutorArrayList);
        List tuteesArrayList=new ArrayList<>(tuteeService.findAll());
        model.addAttribute("tutees", tuteesArrayList);
        List moduleArrayList=new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleArrayList);
        List tuteeModuleArrayList=new ArrayList<>(tuteeModuleService.findAll());
        model.addAttribute("tuteeModules",tuteeModuleArrayList);
        return new ModelAndView("redirect:/admin/dashboard");
    }

    /**
     * Handles the requests to the admin/admin-panel and admin/dashboard URIs.
     * Retrieves all Tutor, Tutee, Module, and TuteeModule objects from the corresponding services and adds them to the model.
     * Retrieves the currently authenticated user's username and adds it to the model.
     * Returns the name of the view "admin/admin-panel".
     * @param request the HttpServletRequest object
     * @param model the model object
     * @return the name of the view "admin/admin-panel"
     */
    @RequestMapping(value = {"admin/admin-panel","admin/dashboard"})
    public String home(HttpServletRequest request, Model model) {
        List tutorArrayList=new ArrayList<>(tutorService.findAll());
        model.addAttribute("tutors", tutorArrayList);
        List tuteesArrayList=new ArrayList<>(tuteeService.findAll());
        model.addAttribute("tutees", tuteesArrayList);
        List moduleArrayList=new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleArrayList);
        // Add the modules attribute to the global scope
        request.setAttribute("modules", moduleArrayList);
        List tuteeModuleArrayList=new ArrayList<>(tuteeModuleService.findAll());
        model.addAttribute("tuteeModules",tuteeModuleArrayList);
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
        }
        return "admin/admin-panel";
    }
}
