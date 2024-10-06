--liquibase formatted sql
--changeset admin
--comment creating db
CREATE TABLE IF NOT EXISTS students (
    id         BIGINT PRIMARY KEY NOT NULL,
    name       VARCHAR(100)       NOT NULL,
    email      VARCHAR(75) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status SMALLINT NOT NULL
);