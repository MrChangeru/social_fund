package com.social_fund.demo.controller;

import com.social_fund.demo.model.User;
import com.social_fund.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "index";
    }
}