package com.dubroushchyk.first_spring.controller;
import com.dubroushchyk.first_spring.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private SecurityService securityService;

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/user";
        }

        if (error != null) {
            model.addAttribute(
                    "errorMessage",
                    "Your username and password is invalid or account blocked.");
        }

        if (logout != null) {
            model.addAttribute(
                    "logOutMessage",
                    "You have been logged out successfully.");
        }

        return "loginPage";
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}