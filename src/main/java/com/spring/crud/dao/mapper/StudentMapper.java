package com.spring.crud.dao.mapper;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.model.response_dto.StudentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface StudentMapper {

    StudentResponse studentToResponse(Student student);
}
