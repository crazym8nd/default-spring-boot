package com.spring.crud.service.impl;

import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.entity.Teacher;
import com.spring.crud.dao.repository.jpa.TeacherRepository;
import com.spring.crud.model.request_dto.TeacherForRequest;
import com.spring.crud.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(final TeacherForRequest teacherrq) {
        final Instant now = Instant.now();
        return teacherRepository.save(Teacher.builder()
                                             .name(teacherrq.name())
                                             .createdAt(now)
                                             .updatedAt(now)
                                             .courses(Collections.emptyList())
                                             .department(null)
                                             .status(Status.ACTIVE)
                                             .build());
    }
}
