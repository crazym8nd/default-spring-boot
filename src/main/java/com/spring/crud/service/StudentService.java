package com.spring.crud.service;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.model.request_dto.StudentForRequest;
import com.spring.crud.model.request_dto.StudentForUpdate;
import com.spring.crud.model.response_dto.InformationResponse;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    Student createStudent(StudentForRequest studentrq);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(Long studentId);

    Student updateStudentById(Long studentId, StudentForUpdate studentForUpdate);

    InformationResponse deleteStudentById(Long studentId);
}

