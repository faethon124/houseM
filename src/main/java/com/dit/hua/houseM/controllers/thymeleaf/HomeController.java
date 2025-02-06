package com.dit.hua.houseM.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage() {
        // This method will simply return the view for the home page
        return "home";  // Name of the Thymeleaf template (home.html)
    }
}


