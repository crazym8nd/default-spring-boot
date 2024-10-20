package com.spring.crud.dao.mapper;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.entity.Student;
import com.spring.crud.dao.entity.Teacher;
import com.spring.crud.model.StudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = Mappers.getMapper(StudentMapper.class);
    }

    @Test
    void studentToResponse() {
        final Instant now = Instant.now();
        final Set<Course> courses = Set.of(Course.builder()
                                                 .id(1L)
                                                 .title("testtitle")
                                                 .createdAt(now)
                                                 .updatedAt(now)
                                                 .status(Status.ACTIVE)
                                                 .teacher(Teacher.builder()
                                                                 .build())
                                                 .students(Collections.emptySet()).build());

        final Student student = Student.builder()
                                       .id(8L)
                                       .name("teststudent")
                                       .email("test@email.com")
                                       .status(Status.ACTIVE)
                                       .createdAt(now)
                                       .updatedAt(now)
                                       .courses(courses)
                                       .build();

        final StudentResponse result = studentMapper.studentToResponse(student);

        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo(student.getName());

    }

}