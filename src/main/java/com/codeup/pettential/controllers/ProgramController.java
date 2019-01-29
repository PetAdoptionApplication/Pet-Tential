package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.models.User;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.UserRepository;
import com.codeup.pettential.services.ProgramServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class ProgramController {

    private final ProgramRepository programDao;
    private final ShelterRepository shelterDao;
    private final UserRepository userDao;
    private final ProgramServices programServices;

    public ProgramController(ProgramRepository programDao, ShelterRepository shelterDao, UserRepository userDao, ProgramServices programServices) {
        this.programDao = programDao;
        this.shelterDao = shelterDao;
        this.userDao = userDao;
        this.programServices = programServices;
    }

    @GetMapping("shelter/createProgram")
    public String createProgram(Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Shelter shelter = shelterDao.findByUser(user1);
        model.addAttribute("program", new Program());
        model.addAttribute("shelters", shelter);
        return "create/createProgram";
    }

    @PostMapping("/shelter/createProgram")
    public String saveProgram(@ModelAttribute Program program, @RequestParam(name = "shelter") Long shelter) {
        Shelter shelter1 = shelterDao.findOne(shelter);
        program.setShelter(shelter1);
        programDao.save(program);
        return "redirect:/home";
    }

    @PostMapping("signup/program/{id}")
    public String signUpForProgram(@PathVariable long id, Model model) throws InterruptedException {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findOne(user1.getId());
        Program program = programDao.findOne(id);
        List<User> programUsers = program.getProgramUsers();
        List<Program> usersPrograms = user.getPrograms();
        Thread.sleep(1000);
        if (programUsers.contains(user)){
            return "redirect:/home";
        }
        programUsers.add(user);
        program.setProgramUsers(programUsers);
        programDao.save(program);
        usersPrograms.add(program);
        user.setPrograms(usersPrograms);
        userDao.save(user);
        for(Program thisProgram : programDao.findAll()){
            if (!usersPrograms.contains(thisProgram)){
                programServices.addToUnsignedProgs(program);
            }
        }
        model.addAttribute("unsignedProgs", programServices.getUnsignedPrograms());
        return "redirect:/home";
    }

    @GetMapping("adopter/programs")
    public String getPrograms(Model model) {
        model.addAttribute("programs", programDao.findAll());
        return "views/programs";
    }

    @GetMapping("adopter/program/{id}")
    public String getAllPrograms(@PathVariable long id, Model model) {
        Program program = programDao.findOne(id);
        List<User> users = program.getProgramUsers();
        model.addAttribute("users", users);
        model.addAttribute("program", program);
        long shelterId = programDao.findOne(id).getShelter().getId();
        model.addAttribute("shelter", shelterDao.findOne(shelterId));
        return "views/program";
    }

    @GetMapping("shelter/program/edit/{id}")
    public String editProgram(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("program", programDao.findOne(id));
        return "edit_pages/editProgram";
    }

    @PostMapping("/editProgram")
    public String saveEditProgram(@ModelAttribute Program program, @RequestParam(name = "id") String id,
                                  Model model) {
        Long thisId = Long.parseLong(id);
        Program editProgram = programDao.findOne(thisId);
        editProgram.setName(program.getName());
        editProgram.setPetType(program.getPetType());
        editProgram.setTime(program.getTime());
        editProgram.setLength(program.getLength());
        editProgram.setDescription(program.getDescription());
        programDao.save(editProgram);
        model.addAttribute("editProgram", "Your program has been edited");
        model.addAttribute("id", id);
        model.addAttribute("program", programDao.findOne(thisId));
        return "edit_pages/editProgram";
    }
}
