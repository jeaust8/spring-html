package com.okayjava.html.homepagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homepage() {
        return "homepage"; // Hier verwijzen we naar homepage.html
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/formdata")
    public String formData() {
        return "formdata";
    }
}





//package com.okayjava.html.controller;
//
//import com.okayjava.html.model.User;
//import com.okayjava.html.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class HomePageController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/")
//    public String homepage() {
//        return "homepage"; // Hier verwijzen we naar homepage.html
//    }
//
//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }
//
//    @PostMapping("/register")
//    public String userRegistration(@ModelAttribute User user, Model model){
//        System.out.println(user.toString());
//        //validate
//        System.out.println(user.getFname());
//        System.out.println(user.getLname());
//        System.out.println(user.getEmail());
//
//        User userInserted = userRepository.save(user);
//        model.addAttribute("message", userInserted.getEmail() + " inserted.");
//
//        return "welcome";
//    }
//}