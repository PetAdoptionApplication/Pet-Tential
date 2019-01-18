package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Shelter;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Column;
import java.util.List;

public interface ShelterRepository extends CrudRepository<Shelter, Long> {
    List<Shelter> findAllByName(String name);
    List<Shelter> findAllByLocation(String location);
}
