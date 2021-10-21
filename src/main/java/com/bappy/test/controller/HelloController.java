package com.bappy.test.controller;

import com.bappy.test.entity.User;
import com.bappy.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class HelloController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/addUser")
    public User addUser(@RequestBody User userBody) {
        return userService.saveUser(userBody);
    }

    @GetMapping(path = "/getUserById")
    public User getUser(@RequestParam("id") int id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            System.out.println("Error occurred!!" + e.getMessage());
            return null;
        }
    }
}
