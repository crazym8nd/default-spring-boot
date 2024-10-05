package com.spring.crud.model;

import java.util.Set;

public record StudentResponse(Long id, String name, String email, Set<CourseResponse> courses) {
}
