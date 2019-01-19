package com.codeup.pettential.repositories;

import com.codeup.pettential.models.App;
import com.codeup.pettential.models.Pet;
import com.codeup.pettential.models.Shelter;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Max;
import java.util.List;

public interface AppRepository extends CrudRepository <App, Long> {
    List<App> findAllByShelter(Shelter shelter);
    App findByPet(Pet pet);
}
