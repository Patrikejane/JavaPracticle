package com.abc.resources;

import com.abc.domain.Student;
import com.abc.dto.AddStudentDto;
import com.abc.dto.StudentDto;
import com.abc.repositories.StudentRepository;
import com.abc.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by SunimalM on 11/30/2018.
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/all")
    public ResponseEntity getAllStudent(){

        ResponseBuilder<List<StudentDto>> allStudentResponse = new ResponseBuilder<>();
        try {
            List<Student> studentlist = studentRepository.findAll();
            if (studentlist.size() > 0) {
                List<StudentDto> studentDtoList = new ArrayList<>();

                studentlist.forEach(st -> {
                    StudentDto studentDto = new StudentDto();
                    studentDto.setStudentName(st.getStudentName());
                    studentDto.setStudentAge(st.getStudentAge());
                    studentDtoList.add(studentDto);
                });

                allStudentResponse.setStatus(HttpStatus.OK.name());
                allStudentResponse.setStatusCode(HttpStatus.OK.value());
                allStudentResponse.setPayload(studentDtoList);
                allStudentResponse.setMessage("all student retrieved Successfully");
                return new ResponseEntity<>(allStudentResponse, HttpStatus.OK);

            } else {
                allStudentResponse.setStatus(HttpStatus.OK.name());
                allStudentResponse.setStatusCode(HttpStatus.OK.value());
                allStudentResponse.setPayload(new ArrayList<>(0));
                allStudentResponse.setMessage("all student retrieved unsuccessful");
                return new ResponseEntity<>(allStudentResponse, HttpStatus.OK);
            }
        } catch (RuntimeException e)
        {
            allStudentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            allStudentResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            allStudentResponse.setPayload(new ArrayList<>(0));
            allStudentResponse.setMessage("Internal Error");
            return new ResponseEntity<>(allStudentResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody AddStudentDto addStudentDto)
    {
        ResponseBuilder<AddStudentDto> addStudentResponse = new ResponseBuilder<>();

        Optional<Student> existingStudent = studentRepository.findByStudentId(addStudentDto.getStudentId());
        if(!existingStudent.isPresent()) {
            Student student = new Student();
            student.setStudentId(addStudentDto.getStudentId());
            student.setStudentName(addStudentDto.getStudentName());
            student.setStudentAge(addStudentDto.getStudentAge());

            studentRepository.save(student);

            addStudentResponse.setStatus(HttpStatus.OK.name());
            addStudentResponse.setStatusCode(HttpStatus.OK.value());
            addStudentResponse.setPayload(addStudentDto);
            addStudentResponse.setMessage("all student retrieved Successfully");
            return new ResponseEntity<>(addStudentResponse, HttpStatus.OK);
        }
        else
        {
            addStudentResponse.setStatus(HttpStatus.NO_CONTENT.name());
            addStudentResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            addStudentResponse.setPayload(addStudentDto);
            addStudentResponse.setMessage("Student Id already Exists");
            return new ResponseEntity<>(addStudentResponse, HttpStatus.NO_CONTENT);
        }

    }


    @GetMapping("/{studentName}")
    public ResponseEntity getAllStudent(@RequestParam String studentName) {

        ResponseBuilder<List<StudentDto>> studentByNameResponse = new ResponseBuilder<>();

        Optional<List<Student>> studentlist = studentRepository.findByStudentName(studentName);

        if(studentlist.isPresent())
        {
            List<StudentDto> studentDtoList = new ArrayList<>();

            studentlist.get().forEach(st -> {
                StudentDto studentDto = new StudentDto();
                studentDto.setStudentName(st.getStudentName());
                studentDto.setStudentAge(st.getStudentAge());
                studentDtoList.add(studentDto);
            });

            studentByNameResponse.setStatus(HttpStatus.OK.name());
            studentByNameResponse.setStatusCode(HttpStatus.OK.value());
            studentByNameResponse.setPayload(studentDtoList);
            studentByNameResponse.setMessage("all student retrieved Successfully");
            return new ResponseEntity<>(studentByNameResponse, HttpStatus.OK);

        }
        else{
            studentByNameResponse.setStatus(HttpStatus.NO_CONTENT.name());
            studentByNameResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            studentByNameResponse.setPayload(new ArrayList<>(0));
            studentByNameResponse.setMessage("all student retrieved Successfully");
            return new ResponseEntity<>(studentByNameResponse, HttpStatus.OK);
        }

    }





}
