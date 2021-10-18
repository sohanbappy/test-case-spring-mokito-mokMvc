package com.bappy.test.controller;

import com.bappy.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    UserService userService;


    @RequestMapping(path = "/index")
    public String getHomePage(Model model) {
        model.addAttribute("msg", "Welcome Home");
        model.addAttribute("type", "guest");
        model.addAttribute("userList", userService.getAllUsers());
        return "index";
    }
}
