--comment creating db
CREATE TABLE IF NOT EXISTS students (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100)       NOT NULL,
    email      VARCHAR(75) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT           NOT NULL
);
CREATE TABLE IF NOT EXISTS courses (
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT     NOT NULL
);
CREATE TABLE IF NOT EXISTS teachers (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT     NOT NULL
);
CREATE TABLE IF NOT EXISTS departments (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    status     SMALLINT     NOT NULL
);