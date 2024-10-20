package com.spring.crud.rest;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.dao.mapper.StudentMapper;
import com.spring.crud.model.StudentForRequest;
import com.spring.crud.model.StudentResponse;
import com.spring.crud.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse createStudent(@RequestBody final StudentForRequest student) {
        final Student createdStudent = studentService.createStudent(student);
        return studentMapper.studentToResponse(createdStudent);
    }


}
