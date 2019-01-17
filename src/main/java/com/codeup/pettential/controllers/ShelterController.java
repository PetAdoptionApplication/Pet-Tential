package com.codeup.pettential.controllers;

import com.codeup.pettential.repositories.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShelterController {

    private final ShelterRepository shelterDao;

    public ShelterController(ShelterRepository shelterDao) {
        this.shelterDao = shelterDao;
    }

//    @GetMapping ("adopter/{id}")
//    public String findUser(@PathVariable Long id, Model model) {
//
//    }


}
