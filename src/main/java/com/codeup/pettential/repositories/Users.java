package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Preferences;
import com.codeup.pettential.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.jws.soap.SOAPBinding;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByPreferences(Preferences preferences);
    User findById(Long id);
}
