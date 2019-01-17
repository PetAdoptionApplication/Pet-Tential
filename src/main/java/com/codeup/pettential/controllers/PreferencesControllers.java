package com.codeup.pettential.controllers;


import com.codeup.pettential.models.Preferences;
import com.codeup.pettential.repositories.PreferencesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PreferencesControllers {

    private final PreferencesRepository preferencesDao;

    public PreferencesControllers(PreferencesRepository preferencesDao) {
        this.preferencesDao = preferencesDao;
    }

    @GetMapping ("adopter/preferences")
    public String createPreference(Model model) {
        model.addAttribute("preference", new Preferences());
        return "preferences";
    }

    @PostMapping ("adopter/preferences")
    public String savePreferences(@ModelAttribute Preferences preferences) {
        preferencesDao.save(preferences);
        return "redirect:/home";
    }



}
