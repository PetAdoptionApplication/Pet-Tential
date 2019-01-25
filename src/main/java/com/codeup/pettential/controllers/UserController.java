package com.codeup.pettential.controllers;

import com.codeup.pettential.models.*;
import com.codeup.pettential.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private ShelterRepository shelterDao;
    private ProgramRepository programDao;
    private AppRepository appDao;
    private UserRepository userDao;
    private PreferencesRepository preferenceDao;
    private VolunteerRepository volDao;

    public UserController(Users users, PasswordEncoder passwordEncoder, ShelterRepository shelterDao, ProgramRepository programDao, AppRepository appDao, UserRepository userDao,
                          PreferencesRepository preferenceDao, VolunteerRepository volDao) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.shelterDao = shelterDao;
        this.programDao = programDao;
        this.appDao = appDao;
        this.userDao = userDao;
        this.preferenceDao = preferenceDao;
        this.volDao = volDao;
    }

    @GetMapping("/")
    public String homePage() {
        return "system/landing";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "system/sign-up";
    }

    @GetMapping("/home")
    public String success(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return "redirect:/login";
        }
        model.addAttribute("programs", programDao.findAll());
        model.addAttribute("app", appDao.findAll());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getIsShelter()) {
            Shelter thisShelter = shelterDao.findByUser(user);
            List<App> appForThisShelter = appDao.findAllByShelter(thisShelter);
            List<Volunteer> volsForThisShelter = volDao.findAllByShelter(thisShelter);
            model.addAttribute("apps", appForThisShelter);
            model.addAttribute("vols", volsForThisShelter);
            return "views/shelter_home";
        }else {
            User user1 = userDao.findOne(user.getId());
            List<Volunteer> volunteers = (List<Volunteer>) volDao.findAll();
            List<Program> programs = (List<Program>) programDao.findAll();
            model.addAttribute("user", user1);
            model.addAttribute("volunteers", volunteers);
            model.addAttribute("programs", programs);
            return "views/adopter_home";
        }
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Model model, @RequestParam(name = "city") String city, @RequestParam(name = "state") String state,
                           @RequestParam(name = "confPass") String confPass){
        if (user.getUsername().equals("") || user.getPassword().equals("") || user.getEmail().equals("") ||
                user.getAddress().equals("") || user.getNumber().equals("")){
            model.addAttribute("error", "All Fields Must be filled in");
            return "system/sign-up";
        }
        if (user.getPassword().length() < 8){
            model.addAttribute("error", "Password Must Be 8 characters in length");
            return "system/sign-up";
        }
        if (!user.getPassword().equals(confPass)){
            model.addAttribute("error", "Passwords do not match");
            return "system/sign-up";
        }
        List<User> users1 = (List<User>) userDao.findAll();
        for (User user1 : users1){
            if (user1.getUsername().equals(user.getUsername())){
                model.addAttribute("error", "Sorry, Username has already been used, please select a different one");
                return "system/sign-up";
            }
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        String address = user.getAddress();
        String newAddress = address + ", " + city + ", " + state;
        user.setAddress(newAddress);
        users.save(user);
        if (user.getIsShelter()){
            Shelter shelter = new Shelter();
            shelter.setName(user.getName());
            shelter.setLocation(user.getAddress());
            shelter.setEmail(user.getEmail());
            shelter.setNumber(user.getNumber());
            shelter.setUser(user);
            shelterDao.save(shelter);
        }
        return "redirect:/login";
    }

    //For the Shelter Registration form
    @GetMapping("/shelter/register/{id}")
    public String createShelter(Model model, @PathVariable long id){
        User user = userDao.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("newShelter", new Shelter());
        return "system/register";
    }

    @PostMapping("/shelter/register/{id}")
    public String saveShelter (@ModelAttribute Shelter newShelter, @PathVariable long id){
        User user = userDao.findOne(id);
        newShelter.setUser(user);
        shelterDao.save(newShelter);
        return "redirect:/login";
    }
}
