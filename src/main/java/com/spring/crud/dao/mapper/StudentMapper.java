package com.spring.crud.dao.mapper;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.model.StudentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponse studentToResponse(Student student);
}
