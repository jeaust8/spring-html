package nl.jva.webapp.controller;

import nl.jva.webapp.model.User;
import nl.jva.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/details/{userid}")
    public String showUserDetails(@PathVariable("userid") Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "userdetails_page";
    }
}
