package com.spring.crud.dao.repository.jpa;

import com.spring.crud.dao.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @EntityGraph(value = "Student.courses", type = EntityGraph.EntityGraphType.LOAD)
    @Query("Select s FROM Student s")
    List<Student> findAllWithCourses();

}
