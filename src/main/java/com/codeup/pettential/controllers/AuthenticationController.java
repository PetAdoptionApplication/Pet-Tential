package com.codeup.pettential.controllers;

import com.codeup.pettential.models.User;
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

    @PostMapping("/login")
    public void loggedIn(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User customUser = (User)authentication.getPrincipal();
        long userId = customUser.getId();
        model.addAttribute("userId", userId);
    }


//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/system/login.html")
//                .failureUrl("/login-error.html")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/system/login.html");
//    }
}
