package com.spring.crud.service;

import com.spring.crud.dao.entity.Department;
import com.spring.crud.model.request_dto.DepartmentForRequest;

public interface DepartmentService {
    Department createDepartment(DepartmentForRequest departmentrq);
}
