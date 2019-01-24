package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Preferences;
import com.codeup.pettential.models.User;
import com.codeup.pettential.models.Volunteer;
import org.springframework.data.repository.CrudRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
