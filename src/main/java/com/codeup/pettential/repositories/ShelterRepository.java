package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Column;
import java.util.List;

public interface ShelterRepository extends CrudRepository<Shelter, Long> {
    Shelter findByUser(User user);
    List<Shelter> findAllByName(String name);
    List<Shelter> findAllByLocation(String location);
}
