package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.models.User;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import com.codeup.pettential.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Controller
public class ProgramController {

    private final ProgramRepository programDao;
    private final ShelterRepository shelterDao;
    private final UserRepository userDao;

    public ProgramController(ProgramRepository programDao, ShelterRepository shelterDao, UserRepository userDao) {
        this.programDao = programDao;
        this.shelterDao = shelterDao;
        this.userDao = userDao;
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
        if(! program.getTime().toLowerCase().substring(program.getTime().length() - 2).equals("am") ||
                ! program.getTime().toLowerCase().substring(program.getTime().length() - 2).equals("pm") ){
            String time = program.getTime();
            time = time + "pm";
            program.setTime(time);
        }
        program.setShelter(shelter1);
        programDao.save(program);
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
