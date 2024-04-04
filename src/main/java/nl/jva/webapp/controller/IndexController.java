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
        return "index_page";
    }

    @PostMapping("/edit")
    public String edit() {
        return "edit_success_page";
    }

//    @PostMapping("/edit")
//    public String saveEdit(@ModelAttribute User user) {
//        try {
//            System.out.println(user.toString()); // Controleer of de gebruiker correct wordt ontvangen
//            User daoUser = userrepo.findById(user.getUserId()).orElse(null); // Gebruiker ophalen uit de database
//            if (daoUser != null) {
//                daoUser.updateUser(user); // Gebruiker bijwerken met de nieuwe gegevens
//                userrepo.save(daoUser); // Opslaan van de bijgewerkte gebruiker in de database
//                return "edit-success"; // Terugkeren naar de succesvolle bewerkingsweergave
//            } else {
//                // Gebruiker niet gevonden, mogelijk moet je een foutmelding weergeven aan de gebruiker
//                return "error-page"; // Terugkeren naar een foutpagina
//            }
//        } catch (Exception e) {
//            // Exception handling - Mogelijk moet je een foutmelding weergeven aan de gebruiker
//            e.printStackTrace();
//            return "error-page"; // Terugkeren naar een foutpagina
//        }
//    }

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

    @GetMapping("/form")
    public String formData() {
        return "form_data";
    }


    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model){

        // Check of het telefoonnummer al bestaat in de database
        if (userrepo.existsByUsername(user.getUsername())) {
            // Telefoonnummer bestaat al, geef een foutmelding terug
            model.addAttribute("error", "Deze gebruikersnaam is reeds in gebruik. Kies een andere gebruikersnaam.");
            return "error_page"; // Maak een Thymeleaf-template genaamd "error_page.html" voor het weergeven van foutmeldingen
        }

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



