package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lab")
public class HelloController {
    @GetMapping("/1")
    public String hello(Model model) {
        return "view/Hello1";
    }
    @GetMapping("/2")
    public String hello2(Model model) {
        model.addAttribute("name", "From hello2...");
        return "view/Hello2";
    }

    @GetMapping("/3")
    public String hello3(Model model) {
        model.addAttribute("name", "From hello3...");
        return "view/Hello3";
    }
}
