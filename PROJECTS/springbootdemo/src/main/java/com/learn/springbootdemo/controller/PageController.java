package com.learn.springbootdemo.controller;

import com.learn.springbootdemo.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PageController {
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("company", "Tech Corp 🌐");
        return "about";
    }

    @GetMapping("/sign-up")
    public String userSignUp() {
        return "register"; // register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        user = null;
        user.getEmail();
        return "success";  // success.html
    }

    @PostMapping("/process-upload")
    @ResponseBody
    public String processUpload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Uploaded file: " + file.getOriginalFilename());
        System.out.println("Content Type: " + file.getContentType());
        System.out.println("Content Byte Size: " + file.getBytes().length);
        return "uploadSuccess";
    }

    @GetMapping("/upload")
    public String upload() {
        return "fileupload";
    }
}
