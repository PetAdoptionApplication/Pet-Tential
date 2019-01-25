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
    @GetMapping("/login")
    public String showLoginForm() {
        return "system/log-in";
    }
}
