package com.spring.crud.rest;

import com.spring.crud.dao.entity.Department;
import com.spring.crud.model.request_dto.DepartmentForRequest;
import com.spring.crud.model.response_dto.DepartmentResponse;
import com.spring.crud.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse createDepartment(@RequestBody final DepartmentForRequest departmentrq) {
        final Department createdDepartment = departmentService.createDepartment(departmentrq);

        return new DepartmentResponse(createdDepartment.getId(), createdDepartment.getName(), null);
    }
}
