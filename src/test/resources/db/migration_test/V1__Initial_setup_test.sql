--comment creating db
CREATE SCHEMA IF NOT EXISTS course_management;
CREATE TABLE IF NOT EXISTS course_management.students (
    id     BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100)       NOT NULL,
    email      VARCHAR(75) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status SMALLINT NOT NULL
);
CREATE TABLE IF NOT EXISTS course_management.teachers (
    id         BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    department_id BIGINT,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT     NOT NULL
);
CREATE TABLE IF NOT EXISTS course_management.courses (
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(100) NOT NULL,
    teacher_id BIGINT,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT     NOT NULL
);
CREATE TABLE IF NOT EXISTS course_management.departments (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    head_of_department_id BIGINT,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT     NOT NULL
);
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