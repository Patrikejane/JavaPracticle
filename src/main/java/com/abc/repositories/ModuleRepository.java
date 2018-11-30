package com.abc.repositories;

import com.abc.domain.Module;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by SunimalM on 11/30/2018.
 */
public interface ModuleRepository extends MongoRepository<Module,String> {

    Optional<Module> findByModuleId(int moduleId);
}
