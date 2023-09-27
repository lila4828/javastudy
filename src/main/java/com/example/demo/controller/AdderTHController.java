package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdderTHController {
    @GetMapping("/th")
    public String view(Model model) {
        return "view/adder";
    }
    @PostMapping("/add-result")
    public String add(
            @RequestParam("num1") int num1,
            @RequestParam("num2") int num2,
            Model model
    ) {
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", num1+num2);
        return "view/adder-result";
    }
}
