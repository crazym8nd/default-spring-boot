--comment creating db
-- Add foreign key constraint to teachers table
ALTER TABLE course_management.teachers
    ADD CONSTRAINT fk_teacher_department
        FOREIGN KEY (department_id)
            REFERENCES course_management.departments(id);

-- Add foreign key constraint to courses table
ALTER TABLE course_management.courses
    ADD CONSTRAINT fk_course_teacher
        FOREIGN KEY (teacher_id)
            REFERENCES course_management.teachers(id);

-- Add foreign key constraint to departments table
ALTER TABLE course_management.departments
    ADD CONSTRAINT fk_department_head
        FOREIGN KEY (head_of_department_id)
            REFERENCES course_management.teachers(id);
CREATE TABLE course_management.student_course (
    student_id BIGINT NOT NULL,
    course_id  BIGINT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id)
        REFERENCES course_management.students(id),
    FOREIGN KEY (course_id)
        REFERENCES course_management.courses(id)
);