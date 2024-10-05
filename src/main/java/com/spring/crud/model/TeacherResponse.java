package com.spring.crud.model;

import java.util.List;

public record TeacherResponse(Long id, String name, List<CourseResponse> courses,
                              DepartmentResponse departmentResponse)
{
}
