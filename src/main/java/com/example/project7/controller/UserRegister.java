package com.example.project7.controller;

import com.example.project7.entity.User;
import com.example.project7.repositroy.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserRegister {

    @Autowired
    UserRepositroy userService;

    @RequestMapping("/")
    public String home(Model model) {
        return "view/index";
    }

    @GetMapping("/register")
    public  String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "view/register_form";
    }

    @PostMapping("/register")
    public String submit(@ModelAttribute("user") User user) {
        userService.save(user);
        return "view/register_success";
    }

}
