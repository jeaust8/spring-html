package nl.jva.webapp.controller;

import nl.jva.webapp.model.User;
import nl.jva.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserRepository userrepo;

    @GetMapping("/home")
    public String homepage(){
        return "home_page";
    }

    @GetMapping("/register")
    public String index(){
        return "register_page";
    }

    @PostMapping("/edit")
    public String edit() {
        return "edit_success_page";
    }

    @GetMapping("/edit_success_page")
        public String editSuccess(){
        return "edit_success_page";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam String keyword, Model model) {
        List<User> users = userrepo.findByFnameContainingOrLnameContainingOrEmailContainingOrPhonenumberContaining(keyword, keyword, keyword, keyword);
        model.addAttribute("users", users);
        return "search_results_page"; // Creëer een nieuw Thymeleaf-template genaamd "search_results_page.html"
    }

    @GetMapping("/search_form")
    public String formData() {
        return "search_page";
    }


    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model){



        // Check of het e-mailadres al bestaat in de database
        if (userrepo.existsByEmail(user.getEmail())) {
            // E-mailadres bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Dit e-mailadres is al geregistreerd. Gebruik een ander e-mailadres.");
            return "error_page"; // Maak een Thymeleaf-template genaamd "error_page.html" voor het weergeven van foutmeldingen
        }

        // Check of het telefoonnummer al bestaat in de database
        if (userrepo.existsByPhonenumber(user.getPhonenumber())) {
            // Telefoonnummer bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Dit telefoonnummer is al geregistreerd. Gebruik een ander telefoonnummer.");
            return "error_page"; // Maak een Thymeleaf-template genaamd "error_page.html" voor het weergeven van foutmeldingen
        }

        // Check of de gebruikersnaam al bestaat in de database
        if (userrepo.existsByUsername(user.getUsername())) {
            // Gebruikersnaam bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Deze gebruikersnaam is reeds in gebruik. Kies een andere gebruikersnaam.");
            return "error_page"; // Maak een Thymeleaf-template genaamd "error_page.html" voor het weergeven van foutmeldingen
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
        return "success_page"; // Maak een Thymeleaf-template genaamd "success_page.html" voor de succesvolle registratiepagina
    }}



