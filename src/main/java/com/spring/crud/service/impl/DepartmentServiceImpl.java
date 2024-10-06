package com.spring.crud.service.impl;

import com.spring.crud.dao.entity.Department;
import com.spring.crud.dao.entity.Status;
import com.spring.crud.dao.repository.jpa.DepartmentRepository;
import com.spring.crud.model.DepartmentForRequest;
import com.spring.crud.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(DepartmentForRequest departmentrq) {
        final Instant now = Instant.now();

        return departmentRepository.save(Department.builder()
                                                   .name(departmentrq.name())
                                                   .createdAt(now)
                                                   .updatedAt(now)
                                                   .status(Status.ACTIVE)
                                                   .headOfDepartment(null)
                                                   .build());
    }
}
