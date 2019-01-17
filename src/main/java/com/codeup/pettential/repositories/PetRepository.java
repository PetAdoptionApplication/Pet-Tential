package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
