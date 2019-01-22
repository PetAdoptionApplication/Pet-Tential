package com.codeup.pettential.controllers;

import com.codeup.pettential.models.*;
import com.codeup.pettential.repositories.PetRepository;
import com.codeup.pettential.repositories.PreferencesRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController {

    private final PetRepository petDao;
    private final PreferencesRepository preferenceDao;
    private final ShelterRepository shelterDao;

    public PetController(PetRepository petDao, PreferencesRepository preferenceDao, ShelterRepository shelterDao) {
        this.petDao = petDao;
        this.preferenceDao = preferenceDao;
        this.shelterDao = shelterDao;
    }

    @GetMapping("create/pet")
    public String createPet(Model model) {
        List<Shelter> shelters = (List<Shelter>) shelterDao.findAll();
        model.addAttribute("shelters", shelters);
        model.addAttribute("pet", new Pet());
        return "shelter/pets";
    }

    @PostMapping("/create/pet")
    public String savePet(@ModelAttribute Pet pet, @RequestParam(name = "sex") String sex,
                          @RequestParam(name = "shelter") long shelter) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return "redirect:/login";
        }
        User user;
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pet.setSex(sex);
        pet.setShelter(shelterDao.findOne(shelter));
        pet.setUser(user1);
        petDao.save(pet);
        user = Twillio.checkPreferences(preferenceDao, pet);
        if (user != null) {
            Twillio.sendMessage(user.getNumber(), "Your prefrence has been pinged");
        }
        return "redirect:/shelter/home";
    }

    @GetMapping("adopter/pet/{id}")
    public String findPet(@PathVariable long id, Model model) {
        model.addAttribute("pet", petDao.findOne(id));
        return "adopter/pet";
    }

    @GetMapping("adopter/pets")
    public String allPets(Model model) {
        model.addAttribute("pets", petDao.findAll());
        return ("adopter/all_pets");
    }

}
