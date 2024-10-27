package com.spring.crud.model.response_dto;

import java.util.Set;

public record CourseResponse(Long id, String title, TeacherResponse teacherResponse,
                             Set<StudentResponse> studentResponses)
{
}
