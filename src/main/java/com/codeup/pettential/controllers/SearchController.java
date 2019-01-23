package com.codeup.pettential.controllers;

import com.codeup.pettential.models.Pet;
import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.repositories.AppRepository;
import com.codeup.pettential.repositories.PetRepository;
import com.codeup.pettential.repositories.ProgramRepository;
import com.codeup.pettential.repositories.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class SearchController {
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private ShelterRepository shelterDao;
    private ProgramRepository programDao;
    private PetRepository petDao;

    public SearchController(ShelterRepository shelterDao, ProgramRepository programDao, PetRepository petDao) {
        this.shelterDao = shelterDao;
        this.programDao = programDao;
        this.petDao = petDao;
    }

    @GetMapping("search")
    public String createProgram() {
        return "system/search";
    }

//    need to implement search by shelter for pets
    @PostMapping("search")
    public String saveProgram(@RequestParam(name = "search") String search, Model model) {
        List<Pet> petByBreed = petDao.findAllByBreed(search);
        List<Pet> petByColor = petDao.findAllByColor(search);
        List<Pet> petByName = petDao.findAllByName(search);
        List<Pet> pets = new ArrayList<>();
        List<Program> programs = new ArrayList<>();
        List<Shelter> shelters = new ArrayList<>();
        List<Program> programByName = programDao.findAllByName(search);
        List<Program> programByType = programDao.findAllByType(search);
        List<Program> programByTime = programDao.findAllByTime(search);
        List<Program> programByPetType = programDao.findAllByPetType(search);
        List<Shelter> shelterByName = shelterDao.findAllByName(search);
        List<Shelter> shelterByLocation = shelterDao.findAllByLocation(search);
        pets.addAll(petByBreed);
        pets.addAll(petByColor);
        pets.addAll(petByName);
        programs.addAll(programByName);
        programs.addAll(programByType);
        programs.addAll(programByTime);
        programs.addAll(programByPetType);
        shelters.addAll(shelterByLocation);
        shelters.addAll(shelterByName);
        if (isNumeric(search)){
            int searchNumber = Integer.parseInt(search);
            List<Pet> petByAge = petDao.findAllByAge(searchNumber);
            List<Pet> petByWeight = petDao.findAllByWeight(searchNumber);
            List<Program> programByLength = programDao.findAllByLength(searchNumber);
            pets.addAll(petByAge);
            pets.addAll(petByWeight);
            programs.addAll(programByLength);
        }
        model.addAttribute("pets", pets);
        model.addAttribute("programs", programs);
        model.addAttribute("shelters", shelters);
        return "search";
    }
}
