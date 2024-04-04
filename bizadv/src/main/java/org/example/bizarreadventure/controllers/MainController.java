package org.example.bizarreadventure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {
    @GetMapping("/index")
    public String getMainPage(){
        return "index";
    }
    @GetMapping("/account")
    public String getProfilePage(){
        return "account";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }
    @GetMapping("/single")
    public String getAnimePage(){
        return "single";
    }
}
