package com.codeup.pettential.repositories;

import com.codeup.pettential.models.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository <Application, Long> {
}
