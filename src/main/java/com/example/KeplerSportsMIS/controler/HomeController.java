package com.example.KeplerSportsMIS.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // This method maps the home page ("/") and returns the home.html template.
    @GetMapping("/")
    public String home() {
        return "homepage";
    }
}
