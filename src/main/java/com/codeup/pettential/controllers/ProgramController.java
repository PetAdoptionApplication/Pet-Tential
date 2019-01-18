package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "createProgram";
    }

    @PostMapping("shelter/createProgram")
    public String saveProgram(@ModelAttribute Program program) {
        programDao.save(program);
        return "redirect:/landing";
    }

    @GetMapping("adopter/programs")
    public String getPrograms(Model model) {
        model.addAttribute("programs", programDao.findAll());
        return "adopter/programs";
    }

    @GetMapping("adopter/program/{id}")
    public String getAllPrograms(@PathVariable long id, Model model) {
        model.addAttribute("program", programDao.findOne(id));
        return "adopter/program";
    }


}
