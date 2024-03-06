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

        // Check of het e-mailadres al bestaat in de database
        if (userrepo.existsByEmail(user.getEmail())) {
            // E-mailadres bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Dit e-mailadres is al geregistreerd.");
            return "errorPage"; // Maak een Thymeleaf-template genaamd "errorPage.html" voor het weergeven van foutmeldingen
        }

        // Check of het telefoonnummer al bestaat in de database
        if (userrepo.existsByPhonenumber(user.getPhonenumber())) {
            // Telefoonnummer bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Dit telefoonnummer is al geregistreerd.");
            return "errorPage"; // Maak een Thymeleaf-template genaamd "errorPage.html" voor het weergeven van foutmeldingen
        }

        System.out.println(user.toString());
        //validate
        System.out.println(user.getUserId());
        System.out.println(user.getFname());
        System.out.println(user.getLname());
        System.out.println(user.getEmail());
        System.out.println(user.getPhonenumber());
        System.out.println(user.getZipcode());

        User user_instered = userrepo.save(user);
        model.addAttribute("Het is gelukt ", user_instered.getFname() + "!");
        model.addAttribute("message", "Uw gegevens met e-mailadres " + user_instered.getEmail() + " zijn opgeslagen!");

        return "success";
    }
}



