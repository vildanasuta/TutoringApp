package com.spring.app.controllers.rest;

import com.spring.common.services.TaskServiceImpl;
import com.spring.dao.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/tasks")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @GetMapping("/")
    private Set<Task> getAllTasks(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    private Task getTask(@PathVariable("id") Long id){
        return taskService.findById(id);
    }

    @PostMapping("/")
    private Long addTask(@RequestBody Task task){
        taskService.save(task);
        return task.getId();
    }

    @PutMapping("/")
    private Task updateTask(@RequestBody Task task){
        taskService.save(task);
        return task;
    }

    @DeleteMapping("/{id}")
    private void deleteTask(@PathVariable("id") Long id){
        taskService.deleteById(id);
    }
}
