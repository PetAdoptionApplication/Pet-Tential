package com.codeup.pettential.repositories;

import com.codeup.pettential.models.App;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository <App, Long> {
}
