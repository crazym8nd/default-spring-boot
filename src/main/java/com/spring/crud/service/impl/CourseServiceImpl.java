package com.spring.crud.service.impl;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.repository.jpa.CourseRepository;
import com.spring.crud.model.request_dto.CourseForRequest;
import com.spring.crud.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Course createCourse(final CourseForRequest courserq) {
        final Instant now = Instant.now();

        return courseRepository.save(Course.builder()
                                           .title(courserq.title())
                                           .createdAt(now)
                                           .updatedAt(now)
                                           .students(Collections.emptySet())
                                           .status(Status.ACTIVE)
                                           .build());
    }
}
