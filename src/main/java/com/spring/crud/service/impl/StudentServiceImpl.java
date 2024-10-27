package com.spring.crud.service.impl;

import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.entity.Student;
import com.spring.crud.dao.repository.jpa.StudentRepository;
import com.spring.crud.model.StudentForRequest;
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
}
