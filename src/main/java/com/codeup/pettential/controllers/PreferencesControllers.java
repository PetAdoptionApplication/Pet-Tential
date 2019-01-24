package com.codeup.pettential.controllers;


import com.codeup.pettential.models.Preferences;
import com.codeup.pettential.models.User;
import com.codeup.pettential.repositories.PreferencesRepository;
import com.codeup.pettential.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PreferencesControllers {

    private final PreferencesRepository preferencesDao;

    public PreferencesControllers(PreferencesRepository preferencesDao, Users userDao) {
        this.preferencesDao = preferencesDao;
    }

    @GetMapping ("adopter/preferences")
    public String createPreference(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Preferences preferences = preferencesDao.findByOwner(user);
        if (preferences != null){
            return "redirect:/adopter/preferences/edit/" + preferences.getId();
        }
        model.addAttribute("preference", new Preferences());
        return "views/preferences";
    }

    @PostMapping ("/adopter/preferences")
    public String savePreferences(@ModelAttribute Preferences preferences) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        preferences.setOwner(user);
        preferencesDao.save(preferences);
        user.setPreferences(preferences);
        return "redirect:/home";
    }


    @GetMapping ("adopter/preferences/edit/{id}")
    public String editPreferencePage(Model model, @PathVariable long id) {
        Preferences oldPref = preferencesDao.findOne(id);
        model.addAttribute("oldPref", oldPref);
        return "edit_pages/preference-edit";
    }

    @PostMapping ("/adopter/preferences/edit/{id}")
    public String editPreferences(@PathVariable long id, @RequestParam(name = "breed") String breed,
                                  @RequestParam(name = "age") String age, @RequestParam(name = "color") String color,
                                  @RequestParam(name = "sex") String sex, @RequestParam(name = "weight") String weight) {
        Preferences preference = preferencesDao.findOne(id);
        preference.setBreed(breed);
        preference.setAge(Integer.parseInt(age));
        preference.setColor(color);
        preference.setSex(sex);
        preference.setWeight(Integer.parseInt(weight));
        preferencesDao.save(preference);
        return "redirect:/home";
    }

}
