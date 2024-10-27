package com.spring.crud.service;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.model.request_dto.CourseForRequest;

public interface CourseService {
    Course createCourse(CourseForRequest courserq);
}
