package com.dit.hua.houseM.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PropertyController {

    @GetMapping("/renter/properties")
    public String showProperties(@RequestParam(required = false) String search, Model model) {
        // Fetch properties from a service or repository (pseudo-code)
        List<Property> properties = fetchProperties(search);

        model.addAttribute("properties", properties);
        return "properties"; // Name of the Thymeleaf template (properties.html)
    }

    private List<Property> fetchProperties(String search) {
        // Implement your property fetching logic here
        // This is just a placeholder
        return List.of(
                new Property("Property 1", "Location 1", 1000),
                new Property("Property 2", "Location 2", 1500)
        );
    }

    public static class Property {
        public String name;
        public String location;
        public double price;

        public Property(String name, String location, double price) {
            this.name = name;
            this.location = location;
            this.price = price;
        }
    }
}
