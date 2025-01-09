//package com.dit.hua.houseM.controllers.thymeleaf;
//
//
//import com.dit.hua.houseM.entities.Property;
//import com.dit.hua.houseM.services.PropertyService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/properties")
//public class PropertyThymeleafController {
//
//    private final PropertyService propertyService;
//
//    public PropertyThymeleafController(PropertyService propertyService) {
//        this.propertyService = propertyService;
//    }
//
//    @GetMapping("/list")
//    public String listProperties(Model model) {
//        model.addAttribute("properties", propertyService.findAll());
//        return "properties/list";
//    }
//
//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model model) {
//        model.addAttribute("property", new Property());
//        return "properties/property-form";
//    }
//
//    @PostMapping("/save")
//    public String saveProperty(@ModelAttribute("property") Property property) {
//        propertyService.save(property);
//        return "redirect:/properties/list";
//    }
//
//    @GetMapping("/showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("propertyId") Long id, Model model) {
//        model.addAttribute("property", propertyService.findById(id));
//        return "properties/property-form";
//    }
//
//    @GetMapping("/delete")
//    public String deleteProperty(@RequestParam("propertyId") Long id) {
//        propertyService.deleteById(id);
//        return "redirect:/properties/list";
//    }
//}
//
