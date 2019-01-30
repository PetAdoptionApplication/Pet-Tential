package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.models.User;
import com.codeup.pettential.models.Volunteer;
import com.codeup.pettential.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VolunteersController {
    private Users users;
    private ShelterRepository shelterDao;
    private ProgramRepository programDao;
    private AppRepository appDao;
    private UserRepository userDao;
    private PreferencesRepository preferenceDao;
    private VolunteerRepository volDao;

    public VolunteersController(Users users, ShelterRepository shelterDao, ProgramRepository programDao, AppRepository appDao, UserRepository userDao, PreferencesRepository preferenceDao,
                                VolunteerRepository volDao) {
        this.users = users;
        this.shelterDao = shelterDao;
        this.programDao = programDao;
        this.appDao = appDao;
        this.userDao = userDao;
        this.preferenceDao = preferenceDao;
        this.volDao = volDao;
    }

    @GetMapping("/getvolunteer")
    public String createVolsPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isShelter()){
            return "redirect:/home";
        }
        model.addAttribute("volunteer", new Volunteer());
        return "edit_pages/volOppurties";
    }

    @PostMapping("/getvolunteer")
    public String createVols(@ModelAttribute Volunteer volunteer){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Shelter shelter = shelterDao.findByUser(user);
        volunteer.setShelter(shelter);
        volDao.save(volunteer);
        return "redirect:/home";
    }


    @GetMapping("/showVolunteers/{id}")
    public String showVols(Model model, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isShelter()){
            return "redirect:/home";
        }
        Volunteer volunteer = volDao.findOne(id);
        List<User> usersForThisOpp = volunteer.getVolunteerUsers();
        model.addAttribute("users", usersForThisOpp);
        return "views/showVolunteer";
    }
}
