package com.codeup.pettential.repositories;

import com.codeup.pettential.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
