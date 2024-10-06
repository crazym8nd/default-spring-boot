--comment creating db
CREATE SCHEMA IF NOT EXISTS course_management;
CREATE TABLE IF NOT EXISTS course_management.students (
    id BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100)       NOT NULL,
    email      VARCHAR(75) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status SMALLINT NOT NULL
);