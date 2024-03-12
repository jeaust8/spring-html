package com.okayjava.html.controller;

import com.okayjava.html.model.User;
import com.okayjava.html.service.UserService;
import com.okayjava.html.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserRepository userrepo;

    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }

    @GetMapping("/register")
    public String index(){
        return "index";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @PostMapping("/edit")
    public String saveEdit(@ModelAttribute User user) {
        System.out.println(user.toString());
        User daoUser = userrepo.findById(user.getUserId()).get();
        daoUser.updateUser(user);
        userrepo.save(daoUser);
        return "edit-success";
    }

    @GetMapping("/edit-success")
        public String editSuccess(){
        return "edit-success";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam String keyword, Model model) {
        List<User> users = userrepo.findByFnameContainingOrLnameContainingOrEmailContainingOrPhonenumberContaining(keyword, keyword, keyword, keyword);
        model.addAttribute("users", users);
        return "search_results"; // Creëer een nieuw Thymeleaf-template genaamd "search_results.html"
    }

    @GetMapping("/form")
    public String formData() {
        return "formdata";
    }


    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model){

        // Check of het telefoonnummer al bestaat in de database
        if (userrepo.existsByUsername(user.getUsername())) {
            // Telefoonnummer bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Gebruikersnaam reeds in gebruik. Kies een andere gebruikersnaam.");
            return "errorPage"; // Maak een Thymeleaf-template genaamd "errorPage.html" voor het weergeven van foutmeldingen
        }

        // Check of het e-mailadres al bestaat in de database
        if (userrepo.existsByEmail(user.getEmail())) {
            // E-mailadres bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Dit e-mailadres is al geregistreerd. Gebruik een ander e-mailadres.");
            return "errorPage"; // Maak een Thymeleaf-template genaamd "errorPage.html" voor het weergeven van foutmeldingen
        }

        // Check of het telefoonnummer al bestaat in de database
        if (userrepo.existsByPhonenumber(user.getPhonenumber())) {
            // Telefoonnummer bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Dit telefoonnummer is al geregistreerd. Gebruik een ander telefoonnummer.");
            return "errorPage"; // Maak een Thymeleaf-template genaamd "errorPage.html" voor het weergeven van foutmeldingen
        }

        // Sla de gebruiker op in de database
        User savedUser = userrepo.save(user);

        // Haal de gegenereerde ID op uit de opgeslagen gebruiker
        Long userId = savedUser.getUserId();

        // Voeg de gegenereerde ID toe aan het model
        model.addAttribute("userId", userId);

        model.addAttribute("voornaam", savedUser.getFname() + "!");
        model.addAttribute("message", "Uw gegevens met e-mailadres " + savedUser.getEmail() + " zijn opgeslagen!");
        // Geef de gebruiker door naar de registratiesuccess-pagina
        return "success"; // Maak een Thymeleaf-template genaamd "success.html" voor de succesvolle registratiepagina
    }}



