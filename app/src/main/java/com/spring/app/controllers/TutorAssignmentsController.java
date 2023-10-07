package com.spring.app.controllers;

import com.spring.common.interfaces.AssignmentService;
import com.spring.common.interfaces.ModuleService;
import com.spring.common.interfaces.TaskService;
import com.spring.dao.models.Assignment;
import com.spring.dao.models.Task;
import com.spring.dao.models.Module;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller class for handling requests sent from Assignments page for Tutors.
 */
@Controller
public class TutorAssignmentsController {
    private final ModuleService moduleService;
    private final TaskService taskService;
    private final AssignmentService assignmentService;

    /**
     * Create a new instance of {@link TutorAssignmentsController}
     * @param moduleService service for handling modules
     * @param taskService service for handling tasks
     * @param assignmentService service for handling assignments
     */
    public TutorAssignmentsController(ModuleService moduleService, TaskService taskService, AssignmentService assignmentService) {
        this.moduleService = moduleService;
        this.taskService = taskService;
        this.assignmentService = assignmentService;
    }


    /**
     * Handles the requests to the /tutor/assignments-tutor or /tutor/assignments-tutor/2 URI.
     * Retrieves a list of all Module objects from the ModuleService and adds it to the model.
     * Retrieves a list of all Assignment objects from the AssignmentService and adds it to the model.
     * Retrieves the currently authenticated user's username and adds it to the model.
     * Returns the name of the view "tutor/assignments-tutor".
     * @param model the model object
     * @param request the HttpServletRequest object
     * @return the name of the view "tutor/assignments-tutor"
     */
    @RequestMapping({ "/tutor/assignments-tutor","/tutor/assignments-tutor/2"} )
    public String home(Model model, HttpServletRequest request) {
         ArrayList<Module> moduleArrayList=new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleArrayList);
        ArrayList<Assignment> assignmentArrayList=new ArrayList<>(assignmentService.findAll());
        model.addAttribute("assignments",assignmentArrayList);
        Authentication auth= (Authentication) request.getUserPrincipal();
        if(auth!=null){
            String username=auth.getName();
            model.addAttribute("username",username);
        }
        return "tutor/assignments-tutor";
    }

    /**
     * Adds an assignment to the database and retrieves modules and all assignments and adds them to the model.
     * @param name the name of the assignment.
     * @param description the description of the assignment.
     * @param moduleId the id of the module associated with the assignment.
     * @param model the model object
     */
    @PostMapping("/tutor/assignments-tutor")
    public void addAssignment(@RequestParam String name, @RequestParam String description, @RequestParam Long moduleId, Model model) {
        Assignment assignment = new Assignment();
        Module module = moduleService.findById(moduleId);
        assignment.setName(name);
        assignment.setDescription(description);
        assignment.setModule(module);
        assignmentService.save(assignment);
        ArrayList<Module> moduleArrayList=new ArrayList<>(moduleService.findAll());
        model.addAttribute("modules",moduleArrayList);
        ArrayList<Assignment> assignmentArrayList=new ArrayList<>(assignmentService.findAll());
        model.addAttribute("assignments",assignmentArrayList);
    }

    /**
     * Adds a task to the database.
     * Creates a new Task object and gives it attributes. Adds a list of assignments
     * to the model as an attribute.
     * Redirects to the tutor/assignments-tutor URI.
     * @param name the name of the task
     * @param description the description of the task
     * @param assignmentId the ID of the assignment associated to the task
     * @param model the model object
     * @return the redirect to the view "tutor/assignments-tutor"
     */
    @PostMapping("/endpoint")
    public String addTask(@RequestParam String name, @RequestParam String description, @RequestParam Long assignmentId, Model model) {
        Task task = new Task();
        Assignment assignment = assignmentService.findById(assignmentId);
        task.setName(name);
        task.setDescription(description);
        task.setAssignment(assignment);
        taskService.save(task);
        ArrayList<Assignment> assignmentArrayList=new ArrayList<>(assignmentService.findAll());
        model.addAttribute("assignments",assignmentArrayList);
        return "redirect:/tutor/assignments-tutor";

    }
}
