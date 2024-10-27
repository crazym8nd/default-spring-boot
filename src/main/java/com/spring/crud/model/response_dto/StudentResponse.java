package com.spring.crud.model.response_dto;

import java.util.Set;

public record StudentResponse(Long id, String name, String email, Set<CourseResponse> courses) {
}
