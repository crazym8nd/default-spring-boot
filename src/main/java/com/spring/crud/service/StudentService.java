package com.spring.crud.service;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.model.StudentForRequest;

import java.util.List;


public interface StudentService {
    Student createStudent(StudentForRequest studentrq);

    List<Student> getAllStudents();
}

