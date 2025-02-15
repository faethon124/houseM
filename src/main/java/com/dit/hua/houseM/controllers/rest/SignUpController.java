package com.dit.hua.houseM.controllers.rest;

import com.dit.hua.houseM.UserDto;
import com.dit.hua.houseM.entities.Admin;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.repositories.AdminRepository;
import com.dit.hua.houseM.repositories.OwnerRepository;
import com.dit.hua.houseM.repositories.RenterRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import jakarta.validation.Valid;

@Controller
public class SignUpController {
    private final AdminRepository adminRepository;
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpController(AdminRepository adminRepository, OwnerRepository ownerRepository,
                            RenterRepository renterRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "sign-up";
    }
//    @Valid
    @PostMapping("/signup")
    public String processSignUp( @ModelAttribute("user") UserDto userDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sign-up";
        }

        if (adminRepository.findByUsername(userDto.getUsername()).isPresent() ||
                ownerRepository.findByUsername(userDto.getUsername()).isPresent() ||
                renterRepository.findByUsername(userDto.getUsername()).isPresent()) {
            model.addAttribute("error", "Username is already taken");
            return "sign-up";
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        switch (userDto.getRole()) {
            case ADMIN:
                Admin admin = new Admin(userDto.getUsername(), userDto.getEmail(), encodedPassword);
                adminRepository.save(admin);
                break;
            case OWNER:
                Owner owner = new Owner(userDto.getUsername(), userDto.getEmail(), encodedPassword);
                ownerRepository.save(owner);
                break;
            case RENTER:
                Renter renter = new Renter(userDto.getUsername(), userDto.getEmail(), encodedPassword);
                renterRepository.save(renter);
                break;
        }

        return "redirect:/login";
    }
}