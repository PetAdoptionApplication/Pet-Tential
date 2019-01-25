package com.codeup.pettential.controllers;

import com.codeup.pettential.models.User;
import com.codeup.pettential.repositories.UserRepository;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthenticationController {

    private UserRepository userDao;

    AuthenticationController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "system/log-in";
    }

    @PostMapping("/login")
    public void loggedIn(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDao.findByUsername(username);
        long userId = user.getId();
        model.addAttribute("user",userId);
    }
}
