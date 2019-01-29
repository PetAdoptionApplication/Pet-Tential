package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Query;
import java.util.List;

public interface ProgramRepository extends JpaRepository<Program,Long>, CrudRepository <Program, Long> {
        List<Program> findAllByName(String name);
        List<Program> findAllByType(String type);
        List<Program> findAllByDescription(String description);
        List<Program> findAllByLength(int length);
        List<Program> findAllByTime(String time);
        List<Program> findAllByPetType(String petType);

        @Query("SELECT programs_id FROM user_programs program WHERE program.user_id not like %userId%")
        long getProgramsIdUserDidntSignFor();

}
