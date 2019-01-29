package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Pet;
import com.codeup.pettential.models.Shelter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findAllByBreed(String breed);
    List<Pet> findAllByColor(String color);
    List<Pet> findAllByName(String name);
    List<Pet> findAllByAge(int age);
    List<Pet> findAllByWeight(int weight);
    List<Pet> findAllByShelter(Shelter shelter);
}
