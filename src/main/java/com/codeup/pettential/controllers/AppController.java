package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.repositories.AppRepository;
import com.codeup.pettential.repositories.PetRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private AppRepository appDao;
    private UserRepository userDao;
    private ShelterRepository shelterDao;
    private PetRepository petDao;

    public AppController(AppRepository appDao, UserRepository userDao, ShelterRepository shelterDao, PetRepository petDao) {
        this.appDao = appDao;
        this.userDao = userDao;
        this.shelterDao = shelterDao;
        this.petDao = petDao;
    }

    @PostMapping("/app")
    public String registerShelter (@ModelAttribute Shelter newShelter){
        shelterDao.save(newShelter);
        return "redirect:/shelter/home";
    }
}
