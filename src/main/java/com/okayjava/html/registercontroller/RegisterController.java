package com.okayjava.html.registercontroller;

import com.okayjava.html.model.User;
import com.okayjava.html.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userrepo;

    @GetMapping("/")
    public String register() {
        return "register"; // Dit verwijst naar het register.html-bestand
    }

    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model){
        System.out.println(user.toString());
        //validate
        System.out.println(user.getFname());
        System.out.println(user.getLname());
        System.out.println(user.getEmail());

        User user_inserted = userrepo.save(user);
        model.addAttribute("message", user_inserted.getEmail() + " opgeslagen.");

        // Verwijs naar een bedankpagina (bijv. saved.html)
        return "saved"; // Dit verwijst naar de saved.html pagina
    }
}
