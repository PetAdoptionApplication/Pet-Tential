package com.codeup.pettential.controllers;

import com.codeup.pettential.models.*;
import com.codeup.pettential.repositories.AppRepository;
import com.codeup.pettential.repositories.PetRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

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
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return "redirect:/login";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pet pet = petDao.findOne(id);
        App app = new App();
        app.setPet(pet);
        app.setShelter(pet.getShelter());
        app.setUser(user);
        appDao.save(app);
        return "redirect:/home";
    }

    @Transactional
    @PostMapping("/adopt/{id}/accept/{id2}")
    public String acceptApp (@PathVariable Long id, @PathVariable Long id2){
        User user = userDao.findOne(id2);
        App app = appDao.findOne(id);
        app.setApprovalStatus(true);
        Twillio.sendMessage(user.getNumber().replaceAll("[\\s\\-()]", ""), "Your adoption request has been accepted! Please contact the shelter for " +
                "more information");
        return "redirect:/home";
    }

    @Transactional
    @PostMapping("/adopt/{id}/reject")
    public String rejectApp (@PathVariable Long id){
        appDao.delete(id);
        return "redirect:/home";
    }
}
