package com.spring.crud.dao.mapper;

import com.spring.crud.dao.entity.Course;
import com.spring.crud.model.CourseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface CourseMapper {
    CourseResponse courseToResponse(Course course);
}
