package com.codeup.pettential.controllers;

import com.codeup.pettential.models.User;
import com.codeup.pettential.repositories.ApplicationRepository;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private ShelterRepository shelterDao;
    private ProgramRepository programDao;
    private ApplicationRepository applicationDao;

    public UserController(Users users, PasswordEncoder passwordEncoder, ShelterRepository shelterDao, ProgramRepository programDao, ApplicationRepository applicationDao) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.shelterDao = shelterDao;
        this.programDao = programDao;
        this.applicationDao = applicationDao;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @GetMapping("/home")
    public String success(Model model){
        model.addAttribute("shelter", shelterDao.findAll());
        model.addAttribute("program", programDao.findAll());
        model.addAttribute("application", applicationDao.findAll());
        return "landing";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }
}
