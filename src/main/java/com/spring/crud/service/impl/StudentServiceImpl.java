package com.spring.crud.service.impl;

import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.entity.Student;
import com.spring.crud.dao.repository.jpa.StudentRepository;
import com.spring.crud.exception.StudentNotFoundException;
import com.spring.crud.model.request_dto.StudentForRequest;
import com.spring.crud.model.request_dto.StudentForUpdate;
import com.spring.crud.model.response_dto.InformationResponse;
import com.spring.crud.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public Student createStudent(final StudentForRequest studentrq) {
        final Instant now = Instant.now();
        return studentRepository.save(Student.builder()
                                             .name(studentrq.name())
                                             .email(studentrq.email())
                                             .courses(Collections.emptySet())
                                             .createdAt(now)
                                             .updatedAt(now)
                                             .status(Status.ACTIVE)
                                             .build());
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAllWithCourses();
    }

    @Override
    public Optional<Student> getStudentById(final Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    @Transactional
    public Student updateStudentById(final Long studentId, final StudentForUpdate studentForUpdate) {
        return getStudentById(studentId)
                .map(studentBeforeUpdate -> {
                    final Student updatedStudent = Student.builder()
                                                          .id(studentBeforeUpdate.getId())
                                                          .name(studentForUpdate.name())
                                                          .email(studentForUpdate.email())
                                                          .createdAt(studentBeforeUpdate.getCreatedAt())
                                                          .updatedAt(Instant.now())
                                                          .status(studentBeforeUpdate.getStatus())
                                                          .courses(studentBeforeUpdate.getCourses() !=
                                                                   null ? studentBeforeUpdate.getCourses() : Collections.emptySet())
                                                          .build();
                    return studentRepository.save(updatedStudent);
                })
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with ID " + studentId + " not found",
                        "/api/v1/students/" + studentId
                ));
    }

    @Override
    @Transactional
    public InformationResponse deleteStudentById(final Long studentId) {
        return getStudentById(studentId)
                .map(studentBeforeDelete -> {
                    studentBeforeDelete.setUpdatedAt(Instant.now());
                    studentBeforeDelete.setStatus(Status.DELETED);
                    studentRepository.save(studentBeforeDelete);
                    return new InformationResponse("Student deleted successfully");
                })
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with ID " + studentId + " not found",
                        "/api/v1/students/" + studentId
                ));
    }
}
