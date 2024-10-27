package com.spring.crud.rest;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.dao.mapper.StudentMapper;
import com.spring.crud.exception.StudentNotFoundException;
import com.spring.crud.model.StudentForRequest;
import com.spring.crud.model.StudentResponse;
import com.spring.crud.service.StudentService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public List<StudentResponse> getAllStudentsList() {
        return studentService.getAllStudents().stream().map(studentMapper::studentToResponse).toList();
    }

    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable @Nonnull final Long id) {
        return studentMapper.studentToResponse(studentService.getStudentById(id).orElseThrow(() ->
                new StudentNotFoundException("Student with ID " + id + " not found", "/api/v1/students/" + id)));
    }

}
