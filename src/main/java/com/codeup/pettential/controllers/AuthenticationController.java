package com.codeup.pettential.controllers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "system/log-in";
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/system/login.html")
                .failureUrl("/login-error.html")
                .and()
                .logout()
                .logoutSuccessUrl("/system/login.html");
    }
}
