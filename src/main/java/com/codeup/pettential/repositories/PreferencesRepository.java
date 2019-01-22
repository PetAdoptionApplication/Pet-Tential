package com.codeup.pettential.repositories;


import com.codeup.pettential.models.Preferences;
import com.codeup.pettential.models.User;
import org.springframework.data.repository.CrudRepository;

public interface PreferencesRepository extends CrudRepository <Preferences, Long> {
    Preferences findByOwner(User user);
}
