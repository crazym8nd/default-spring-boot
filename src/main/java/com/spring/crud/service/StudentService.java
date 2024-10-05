package com.spring.crud.service;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.model.StudentForRequest;


public interface StudentService {
    Student createStudent(StudentForRequest studentrq);
}

