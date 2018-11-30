package com.abc.repositories;

import com.abc.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by SunimalM on 11/30/2018.
 */
public interface StudentRepository extends MongoRepository<Student,String> {

    Optional<List<Student>> findByStudentName(String studentName);
    Optional<Student> findByStudentId(int studentid);
}
