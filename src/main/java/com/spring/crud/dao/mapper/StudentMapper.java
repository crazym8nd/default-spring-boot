package com.spring.crud.dao.mapper;

import com.spring.crud.dao.entity.Student;
import com.spring.crud.model.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentResponse studentToResponse(Student student);
}
