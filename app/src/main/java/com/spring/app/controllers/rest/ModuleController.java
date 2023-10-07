package com.spring.app.controllers.rest;

import com.spring.common.services.ModuleServiceImpl;
import com.spring.dao.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/modules")
public class ModuleController {

    @Autowired
    ModuleServiceImpl moduleService;

    @GetMapping("/")
    private Set<Module> getAllModules(){
        return moduleService.findAll();
    }

    @GetMapping("/{id}")
    private Module getModule(@PathVariable("id") Long id){
        return moduleService.findById(id);
    }

    @PostMapping("/")
    private Long addModule(@RequestBody Module module){
        moduleService.save(module);
        return module.getModule_id();
    }

    @PutMapping("/")
    private Module updateModule(@RequestBody Module module){
        moduleService.save(module);
        return module;
    }

    @DeleteMapping("/{id}")
    private void deleteModule(@PathVariable("id") Long id){
        moduleService.deleteById(id);
    }

}
