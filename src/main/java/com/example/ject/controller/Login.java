package com.example.ject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Login {

    @GetMapping("/")
    public String home(Model model) {
        return "view/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "view/login";
    }

    @PostMapping("/register")
    public String user_add(
            @RequestParam("firstName") String firstname,
            @RequestParam("lastName") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {
        User user = new User(firstname, lastname, email, password);

        System.out.println(firstname + lastname + email + password);

        /*model.addAttribute("firstName", firstname);
        model.addAttribute("lastName", lastname);
        model.addAttribute("email", email);*/
        return "view/register";
    }
}
