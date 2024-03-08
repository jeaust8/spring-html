package com.okayjava.html.controller;

import com.okayjava.html.model.User;
import com.okayjava.html.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/details/{userId}")
    public String showUserDetails(@PathVariable("userId") Long userId, Model model) {
        // Gebruikersgegevens ophalen op basis van userId
        User user = userService.getUserById(userId);

        // Voeg gebruikersgegevens toe aan het model
        model.addAttribute("user", user);

        // Teruggeven van de naam van de HTML-template waarin je de gebruikersgegevens weergeeft
        return "userdetails";
    }

        @PostMapping("/userdetails")
        public String saveUserDetails(@ModelAttribute User user, Model model) {
            // Voer hier de logica uit voor het opslaan van gebruikersdetails
            userService.updateUser(user);
            model.addAttribute("message", "Gebruikersgegevens succesvol bijgewerkt!");
            return "userdetails";
        }
}
