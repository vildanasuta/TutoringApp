package com.spring.app.controllers.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {


    @GetMapping("user")
    public String getUser()
    {
        return ("<h1>this is user</h1>");
    }


    @GetMapping("test/")
    public String testRoute(){
        return "bez securotija";
    }



}
