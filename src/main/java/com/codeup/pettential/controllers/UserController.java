package com.codeup.pettential.controllers;

import com.codeup.pettential.models.*;

import com.codeup.pettential.repositories.*;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/editUser")
    public String getEditUserForm(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser = userDao.findOne(user.getId());
        model.addAttribute("user", thisUser);
        return "edit_pages/editUser";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam(name = "name") String name, @RequestParam(name = "address") String address,
                           @RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
                           @RequestParam(name = "number") String number, @RequestParam(name = "id") String id,
                           Model model){
        Long idAsLong = Long.parseLong(id);
        User thisUser = userDao.findOne(idAsLong);
        thisUser.setName(name);
        thisUser.setAddress(address);
        thisUser.setUsername(username);
        thisUser.setEmail(email);
        thisUser.setNumber(number);
        userDao.save(thisUser);
        if (thisUser.isShelter()){
            Shelter shelter = shelterDao.findByUser(thisUser);
            shelter.setName(name);
            shelter.setLocation(address);
            shelter.setNumber(number);
            shelter.setEmail(email);
            shelterDao.save(shelter);
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser1 = userDao.findOne(user.getId());
        model.addAttribute("user", thisUser1);
        model.addAttribute("ProfileUpdate", "Profile has been updated");
        return "edit_pages/editUser";
    }

    @GetMapping("/home")
    public String success(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return "redirect:/login";
        }
        List<Program> programs = (List<Program>) programDao.findAll();
        model.addAttribute("programs", programs);
        model.addAttribute("app", appDao.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDao.findByUsername(username);
        Preferences preferences = user.getPreferences();
        boolean hasPreference = false;
        boolean doesNotHavePreference = false;
        if (preferences == null) {
            doesNotHavePreference = true;
        } else {
            hasPreference = true;
        }

        model.addAttribute("hasPreference", hasPreference);
        model.addAttribute("doesNotHavePreference", doesNotHavePreference);
        model.addAttribute("userId", user.getId());

        if (user.getIsShelter()) {
            Shelter thisShelter = shelterDao.findByUser(user);
            List<App> appForThisShelter = appDao.findAllByShelter(thisShelter);
            List<Volunteer> volsForThisShelter = volDao.findAllByShelter(thisShelter);
            List<Program> programsForThisShelter = programDao.findAllByShelter(thisShelter);
            model.addAttribute("apps", appForThisShelter);
            model.addAttribute("vols", volsForThisShelter);
            model.addAttribute("programs", programsForThisShelter);
            return "views/shelter_home";
        }else {
            User user1 = userDao.findOne(user.getId());
            List<Volunteer> volunteers = (List<Volunteer>) volDao.findAll();
            List<Volunteer> volunteersForThisUser = new ArrayList<>();
            List<Program> programsForThisUser = new ArrayList<>();
            for (Volunteer volunteer : volunteers){
                if (volunteer.getVolunteerUsers().contains(user1)){
                    volunteersForThisUser.add(volunteer);
                }
            }
            for (Program program : programs){
                if (program.getProgramUsers().contains(user1)){
                    programsForThisUser.add(program);
                }
            }
            model.addAttribute("progs", programsForThisUser);
            model.addAttribute("vols", volunteersForThisUser);
            model.addAttribute("user", user1);
            model.addAttribute("volunteers", volunteers);
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
