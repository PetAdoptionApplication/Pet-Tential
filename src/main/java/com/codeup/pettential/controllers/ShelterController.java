package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShelterController {

    private final ShelterRepository shelterDao;
    private final UserRepository userDao;

    public ShelterController(ShelterRepository shelterDao, UserRepository userDao) {
        this.shelterDao = shelterDao;
        this.userDao = userDao;
    }

    @GetMapping ("adopter/{id}")
    public String findUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userDao.findOne(id));
        return "shelter/user";
    }

    @GetMapping("shelter/{id}")
    public String findShelter(@PathVariable long id, Model model) {
        Shelter currentShelter = shelterDao.findOne(id);
        model.addAttribute("shelter", currentShelter);
        model.addAttribute("pets", currentShelter.getPets());
        model.addAttribute("programs", currentShelter.getPrograms());
        return "shelter";
    }

    @GetMapping("shelter/home")
    public String getShelterHome(){
        return "shelter/home";
    }
}
