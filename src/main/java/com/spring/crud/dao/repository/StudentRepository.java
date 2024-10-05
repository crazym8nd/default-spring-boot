package com.spring.crud.dao.repository;

import com.spring.crud.dao.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
