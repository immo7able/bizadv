package org.example.bizarreadventure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        return "register";
    }
}
