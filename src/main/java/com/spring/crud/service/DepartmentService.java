package com.spring.crud.service;

import com.spring.crud.dao.entity.Department;
import com.spring.crud.model.DepartmentForRequest;

public interface DepartmentService {
    Department createDepartment(DepartmentForRequest departmentrq);
}
