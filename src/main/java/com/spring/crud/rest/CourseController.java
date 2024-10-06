package com.spring.crud.rest;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.model.CourseForRequest;
import com.spring.crud.model.CourseResponse;
import com.spring.crud.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@RequestBody final CourseForRequest courserq) {
        final Course createdCourse = courseService.createCourse(courserq);

        return new CourseResponse(createdCourse.getId(), createdCourse.getTitle(), null, Collections.emptySet());
    }
}
