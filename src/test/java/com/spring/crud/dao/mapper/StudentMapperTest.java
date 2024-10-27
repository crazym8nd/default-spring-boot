package com.spring.crud.dao.mapper;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.entity.Student;
import com.spring.crud.dao.entity.Teacher;
import com.spring.crud.model.response_dto.StudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;


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
        assertThat(result.courses()).isNotNull();

    }

}