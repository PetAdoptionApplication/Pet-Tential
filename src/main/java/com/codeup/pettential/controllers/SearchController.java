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
    public String createProgram(Model model) {
        model.addAttribute("noResultPet", "Search Results Displayed Here");
        model.addAttribute("noResultProgram","Search Results Displayed Here");
        model.addAttribute("noResultShelter", "Search Results Displayed Here");
        return "system/search";
    }

//    need to implement search by shelter for pets
    @PostMapping("/search")
    public String saveProgram(@RequestParam(name = "search") String search, Model model) {
        String searchLower = search.toLowerCase();
        if (search.equals("")){
            model.addAttribute("noResultPet", "No Pets Found...");
            model.addAttribute("noResultProgram", "No Programs Found...");
            model.addAttribute("noResultShelter", "No Shelters Found...");
            return "system/search";
        }
        List<Pet> petAll = (List<Pet>) petDao.findAll();
        List<Shelter> shelterAll = (List<Shelter>) shelterDao.findAll();
        List<Program> programsAll = (List<Program>) programDao.findAll();
        List<Pet> petsSearch = new ArrayList<>();
        List<Program> programsSearch = new ArrayList<>();
        List<Shelter> sheltersSearch = new ArrayList<>();
        List<Pet> petsSearchNumber = new ArrayList<>();
        for (Pet pet : petAll){
            if (pet.getName().toLowerCase().contains(searchLower) || pet.getBreed().toLowerCase().contains(searchLower) ||
                    pet.getDescription().toLowerCase().contains(searchLower) || pet.getSex().toLowerCase().contains(searchLower) ||
                    pet.getColor().toLowerCase().contains(searchLower)){
                if (!pet.getName().toLowerCase().equals("deleted")){
                    petsSearch.add(pet);
                }
            }
        }
        for (Shelter shelter : shelterAll){
            if (shelter.getName().toLowerCase().contains(searchLower) || shelter.getLocation().toLowerCase().contains(searchLower)){
                sheltersSearch.add(shelter);
            }
        }
        for (Program program : programsAll){
            if (program.getName().toLowerCase().contains(searchLower) || program.getPetType().toLowerCase().contains(searchLower) ||
                    program.getDescription().toLowerCase().contains(searchLower) || program.getType().toLowerCase().contains(searchLower)){
                programsSearch.add(program);
            }
        }
        if (isNumeric(search)){
            int searchAsNumber = Integer.parseInt(search);
            for (Pet pet : petAll){
                if (pet.getAge() == searchAsNumber || pet.getWeight() == searchAsNumber){
                    petsSearchNumber.add(pet);
                }
            }
        }
        for (Pet pet : petsSearchNumber){
            if (!petsSearch.contains(pet)){
                petsSearch.add(pet);
            }
        }

        if (petsSearch.size() == 0){
            model.addAttribute("noResultPet", "No Pets Found...");
        }
        if (programsSearch.size() == 0){
            model.addAttribute("noResultProgram", "No Programs Found...");
        }
        if (sheltersSearch.size() == 0){
            model.addAttribute("noResultShelter", "No Shelters Found...");
        }
        model.addAttribute("pets", petsSearch);
        model.addAttribute("programs", programsSearch);
        model.addAttribute("shelters", sheltersSearch);
        return "system/search";
    }
}
