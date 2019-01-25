package com.codeup.pettential.repositories;

import com.codeup.pettential.models.User;
import com.codeup.pettential.models.Volunteer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
