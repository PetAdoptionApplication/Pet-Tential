package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.repositories.ProgramRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProgramController {

    private final ProgramRepository programDao;

    public ProgramController(ProgramRepository programDao) {
        this.programDao = programDao;
    }

    @GetMapping("shelter/createProgram")
    public String createProgram(Model model) {
        model.addAttribute("program", new Program());
        return "redirect:/landing";
    }

    @PostMapping("shelter/createProgram")
    public String saveProgram(@ModelAttribute Program program) {
        programDao.save(program);
        return "redirect:/landing";
    }

    @GetMapping("adopter/programs")
    public String getPrograms(Model model) {
        model.addAttribute("program", programDao.findAll());
        return "adopter/programs";
    }

    @GetMapping("adopter/program/{id}")
    public String getAllPrograms(@PathVariable long id, Model model) {
        model.addAttribute("program", programDao.findOne(id));
        return "adopter/program";
    }


}
