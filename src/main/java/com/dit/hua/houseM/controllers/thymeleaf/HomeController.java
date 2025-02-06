package com.dit.hua.houseM.controllers.thymeleaf;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // This will return "home.html" from src/main/resources/templates/
    }
}

