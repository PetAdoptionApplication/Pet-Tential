package com.codeup.pettential.controllers;

import com.codeup.pettential.models.*;
import com.codeup.pettential.repositories.*;
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
    private final UserRepository userDao;
    private final Users userDao1;

    public PetController(PetRepository petDao, PreferencesRepository preferenceDao, ShelterRepository shelterDao, UserRepository userDao, Users userDao1) {
        this.userDao = userDao;
        this.petDao = petDao;
        this.preferenceDao = preferenceDao;
        this.shelterDao = shelterDao;
        this.userDao1 =  userDao1;
    }

    @GetMapping("create/pet")
    public String createPet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.isShelter() != true){
            return "redirect:/home";
        }
        List<Shelter> shelters = (List<Shelter>) shelterDao.findAll();
        model.addAttribute("shelters", shelters);
        model.addAttribute("pet", new Pet());
        return "create/create_pet";
    }

    @PostMapping("/create/pet")
    public String savePet(@ModelAttribute Pet pet, @RequestParam(name = "sex") String sex) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pet.setSex(sex);
        Shelter shelter1 = shelterDao.findByUser(user1);
        pet.setShelter(shelter1);
        pet.setUser(user1);
        if (pet.getPicture().equals("")){
            pet.setPicture("https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg");
        }
        petDao.save(pet);
        List<Preferences> preferences  = (List<Preferences>) preferenceDao.findAll();
        for (Preferences preference : preferences){
         if (preference.getBreed().contains(pet.getBreed()) || preference.getColor().contains(pet.getColor()) ||
                 preference.getSex().contains(pet.getSex()) || preference.getAge() == pet.getAge()
                        || preference.getWeight() == pet.getWeight()) {
                User user2 = userDao.findOne(preference.getOwner().getId());
                Twillio.sendMessage(user2.getNumber(), "A animal matching your preference has been added to the site. " +
                        "Please log in to meet " + pet.getName() + ".");
                }
            }
        return "redirect:/shelter/home";
    }

    @GetMapping("adopter/pet/{id}")
    public String findPet(@PathVariable long id, Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            User user = new User();
            user.setIsShelter(true);
            model.addAttribute("pet", petDao.findOne(id));
            long shelterId = petDao.findOne(id).getShelter().getId();
            model.addAttribute("shelter", shelterDao.findOne(shelterId));
            model.addAttribute("user", user);
            return "views/pet_view";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userDao.findOne(user.getId());
        model.addAttribute("pet", petDao.findOne(id));
        long shelterId = petDao.findOne(id).getShelter().getId();
        model.addAttribute("shelter", shelterDao.findOne(shelterId));
        model.addAttribute("user", user1);
        return "views/pet_view";
    }

    @GetMapping("adopter/pets")
    public String allPets(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            User user = new User();
            user.setIsShelter(false);
            model.addAttribute("user", user);
            model.addAttribute("pets", petDao.findAll());
            return "views/all_pets";
        }
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user1.getId();
        model.addAttribute("pets", petDao.findAll());
        model.addAttribute("user", userDao.findOne(userId));
        return "views/all_pets";
    }

    @PostMapping("/pet/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        Pet pet = petDao.findOne(id);
        pet.setName("deleted");
        petDao.save(pet);
        return "redirect:/adopter/pets";
    }

}
