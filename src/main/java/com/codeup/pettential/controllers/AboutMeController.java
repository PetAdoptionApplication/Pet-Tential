package com.codeup.pettential.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {

    @GetMapping("/aboutme")
    public String aboutMe() {
        return "views/about_me";
    }

}
