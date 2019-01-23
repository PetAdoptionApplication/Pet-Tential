package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProgramController {

    private final ProgramRepository programDao;
    private final ShelterRepository shelterDao;

    public ProgramController(ProgramRepository programDao, ShelterRepository shelterDao) {
        this.programDao = programDao;
        this.shelterDao = shelterDao;
    }

    @GetMapping("shelter/createProgram")
    public String createProgram(Model model) {
        List<Shelter> sheleters = (List<Shelter>) shelterDao.findAll();
        model.addAttribute("program", new Program());
        model.addAttribute("shelters", sheleters);
        return "create/createProgram";
    }

    @PostMapping("shelter/createProgram")
    public String saveProgram(@ModelAttribute Program program, @RequestParam(name = "shelter") Long shelter) {
        Shelter shelter1 = shelterDao.findOne(shelter);
        program.setShelter(shelter1);
        programDao.save(program);
        return "redirect:/home";
    }

    @GetMapping("adopter/programs")
    public String getPrograms(Model model) {
        model.addAttribute("programs", programDao.findAll());
        return "views/programs";
    }

    @PostMapping("adopter/program/{id}")
    public String getAllPrograms(@PathVariable long id, Model model) {
        model.addAttribute("program", programDao.findOne(id));
        long shelterId = programDao.findOne(id).getShelter().getId();
        model.addAttribute("shelter", shelterDao.findOne(shelterId));
        return "view/program";
    }

    @PostMapping("shelter/program/edit/{id}")
    public String editProgram(@PathVariable long id, Model model) {
        model.addAttribute("program", programDao.findOne(id));
        return "edit_pages/editProgram";
    }

    @PostMapping("/editProgram")
    public String saveEditProgram(@ModelAttribute Program program, @RequestParam(name = "shelter") Long shelter) {
        Shelter shelter1 = shelterDao.findOne(shelter);
        program.setShelter(shelter1);
        programDao.save(program);
        return "redirect:/home";
    }
}
