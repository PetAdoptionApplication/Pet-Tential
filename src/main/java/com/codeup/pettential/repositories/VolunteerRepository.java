package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Shelter;
import com.codeup.pettential.models.Volunteer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    List<Volunteer> findAllByShelter(Shelter shelter);
}
