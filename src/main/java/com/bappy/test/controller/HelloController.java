package com.bappy.test.controller;

import com.bappy.test.entity.User;
import com.bappy.test.repository.UserRepo;
import com.bappy.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
public class HelloController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;


    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello!!";
    }

    @GetMapping(path = "/allUsers")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping(path = "/addUser")
    public User addUser(@RequestBody User userBody) {
        return userRepo.save(userBody);
    }
}
