package com.spring.crud.model.response_dto;

import java.util.List;

public record TeacherResponse(Long id, String name, List<CourseResponse> courses,
                              DepartmentResponse departmentResponse)
{
}
