package com.spring.crud.model;

import java.util.Set;

public record CourseResponse(Long id, String title, TeacherResponse teacherResponse,
                             Set<StudentResponse> studentResponses)
{
}
