package com.spring.crud.service;

import com.spring.crud.dao.entity.Teacher;
import com.spring.crud.model.request_dto.TeacherForRequest;

public interface TeacherService {
    Teacher createTeacher(TeacherForRequest teacherrq);
}
