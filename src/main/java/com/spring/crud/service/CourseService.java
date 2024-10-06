package com.spring.crud.service;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.model.CourseForRequest;

public interface CourseService {
    Course createCourse(CourseForRequest courserq);
}
