package com.codeup.pettential.controllers;

import com.codeup.pettential.models.App;
import com.codeup.pettential.models.Pet;
import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.models.User;
import com.codeup.pettential.repositories.AppRepository;
import com.codeup.pettential.repositories.PetRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/adopt/pet/{id}")
    public String submitApp (@ModelAttribute Shelter newShelter, @PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pet pet = petDao.findOne(id);
        App app = new App();
        app.setPet(pet);
        app.setShelter(pet.getShelter());
        app.setUser(user);
        appDao.save(app);
        return "redirect:/shelter/home";
    }
}
