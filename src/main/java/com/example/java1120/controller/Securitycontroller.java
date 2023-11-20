package com.example.java1120.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String index() {
        System.out.println("index 요청입니다.");
        return "index";
    }

    @GetMapping("/member")  //templates/member.html 실행
    public void forMember() {
        System.out.println("Member 요청입니다.");
    }

    @GetMapping("/manager")
    public String  forManager() {
        System.out.println("Manager 요청입니다.");

        return "/manager/manager";
    }

    @GetMapping("/admin")
    public String forAdmin() {
        System.out.println("Admin 요청입니다.");
        return "/admin/admin_page";
    }


    @GetMapping("/login") //templates/login.html 실행
    public String login() {
        return "/login";
    }

    @GetMapping("/loginSuccess") //templates/loginSuccess.html 실행
    public String loginSuccess() {
        return "/loginSuccess";
    }

    @GetMapping("/logout_page")
    public String logout() {
        System.out.println("logout");
        return "logout_page";
    }

    @GetMapping("/accessDenied") //templates/accessDenied.html 실행
    public void accessDenied() {
    }

    @GetMapping("/conditional-content") //templates/conditional-content.html 실행
    public void conditionalContent() {
    }

    @GetMapping("/인증")
    public String authenticate() {
        return "/인증";
    }

    @GetMapping("/인가")
    public String authorize() {
        return "/인가";
    }

}
