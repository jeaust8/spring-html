package com.okayjava.html.controller;

import com.okayjava.html.model.User;
import com.okayjava.html.repo.UserRepository;
import com.okayjava.html.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/details/{userid}")
    public String showUserDetails(@PathVariable("userid") Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "userdetails";
    }

    @PostMapping("/user/details{userId}")
    public ResponseEntity<String> updateUserDetails(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("Gebruikersgegevens succesvol bijgewerkt");
    }

    @GetMapping("/edit/{userId}")
    public ModelAndView editUserPage(@PathVariable(name = "userId") Long userId){
        ModelAndView modelAndView = new ModelAndView("edituser");
        User user = userService.getUserById(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
@PostMapping(path = "{userId}")
@ResponseBody
    public String updateUser(@PathVariable("userId") User user){
        userService.updateUser(user);
        return "Gebruiker succesvol bijgewerkt!";
    }

}
