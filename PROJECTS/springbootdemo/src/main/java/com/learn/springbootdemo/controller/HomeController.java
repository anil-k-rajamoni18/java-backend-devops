package com.learn.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/api/home")
    @ResponseBody
    public String homePage(Model model) {
        model.addAttribute("title", "Welcome to Spring MVC 🏠");
        return "home"; // returns home.jsp or home.html
    }
}
