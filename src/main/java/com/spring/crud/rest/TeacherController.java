package com.spring.crud.rest;

import com.spring.crud.dao.entity.Teacher;
import com.spring.crud.model.request_dto.TeacherForRequest;
import com.spring.crud.model.response_dto.TeacherResponse;
import com.spring.crud.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse createTeacher(@RequestBody final TeacherForRequest teacherrq) {
        final Teacher registeredTeacher = teacherService.createTeacher(teacherrq);

        return new TeacherResponse(
                registeredTeacher.getId(),
                registeredTeacher.getName(),
                Collections.emptyList(),
                null
        );
    }
}
