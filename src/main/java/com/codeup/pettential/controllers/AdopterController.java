package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.User;
import com.codeup.pettential.models.Volunteer;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.UserRepository;
import com.codeup.pettential.repositories.VolunteerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdopterController {

    private final UserRepository userDao;
    private final ProgramRepository programDao;
    private final VolunteerRepository volDao;

    public AdopterController (UserRepository userDao, ProgramRepository programDao, VolunteerRepository volDao){
        this.userDao = userDao;
        this.programDao = programDao;
        this.volDao = volDao;
    }

    @PostMapping("/home")
    public String homePostMap (String type, long id){
        if (type.equals("program")){
            signUpForProgram(id);
        }
        if (type.equals("volunteer")){
            volunteer(id);
        }
        return "redirect:/home";
    }


    private void signUpForProgram(long id) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findOne(user1.getId());
        Program program = programDao.findOne(id);
        List<User> programUsers = program.getProgramUsers();
        List<Program> usersPrograms = user.getPrograms();
        programUsers.add(user);
        program.setProgramUsers(programUsers);
        programDao.save(program);
        usersPrograms.add(program);
        user.setPrograms(usersPrograms);
        userDao.save(user);
    }


    private void volunteer(long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userDao.findOne(user.getId());
        Volunteer volunteer = volDao.findOne(id);
        List<User> volunteers = volunteer.getVolunteerUsers();
        List<Volunteer> oppurtunity = user1.getVolunteers();
        volunteers.add(user1);
        oppurtunity.add(volunteer);
        volunteer.setVolunteerUsers(volunteers);
        volDao.save(volunteer);
        userDao.save(user1);
    }
}
