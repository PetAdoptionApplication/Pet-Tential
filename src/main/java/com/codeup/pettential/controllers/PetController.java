package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Pet;
import com.codeup.pettential.repositories.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetController {

    private final PetRepository petDao;

    public PetController(PetRepository petDao) {
        this.petDao = petDao;
    }

    @GetMapping("create/pet")
    public String createPet(Model model) {
        model.addAttribute("pet", new Pet());
        return "shelter/pets";
    }

    @PostMapping("create/pet")
    public String savePet(@ModelAttribute Pet pet, @RequestParam(name = "sex") String sex) {
        pet.setSex(sex);
        petDao.save(pet);
        return "redirect:/home";
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
