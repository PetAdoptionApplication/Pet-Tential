package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Program;
import com.codeup.pettential.models.Shelter;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Query;
import java.util.List;

public interface ProgramRepository extends CrudRepository <Program, Long> {
        List<Program> findAllByName(String name);
        List<Program> findAllByType(String type);
        List<Program> findAllByDescription(String description);
        List<Program> findAllByLength(int length);
        List<Program> findAllByTime(String time);
        List<Program> findAllByPetType(String petType);
        List<Program> findAllByShelter(Shelter shelter);
}
