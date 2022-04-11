package com.example.first.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("title", "Main page");
        return "Main";
    }
}
