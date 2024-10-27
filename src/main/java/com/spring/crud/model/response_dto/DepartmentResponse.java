package com.spring.crud.model.response_dto;

public record DepartmentResponse(Long id, String name, TeacherResponse headOfDepartment) {
}
