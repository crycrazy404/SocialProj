--liquibase formatted sql

--changeset dev:1 labels:release-1.0
CREATE TABLE roles (
                       id   BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);
--rollback DROP TABLE roles;

--changeset dev:2 labels:release-1.0
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');
--rollback DELETE FROM roles WHERE name IN ('USER', 'ADMIN');

--changeset dev:3 labels:release-1.0
CREATE TABLE users (
                       id              BIGSERIAL PRIMARY KEY,
                       username        VARCHAR(50)  NOT NULL UNIQUE,
                       email           VARCHAR(255) NOT NULL UNIQUE,
                       firstname       VARCHAR(50)  NOT NULL,
                       surname         VARCHAR(50)  NOT NULL,
                       hashed_password VARCHAR(255) NOT NULL,
                       created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       is_deleted      BOOLEAN DEFAULT FALSE
);
--rollback DROP TABLE users;

--changeset dev:4 labels:release-1.0
CREATE INDEX idx_users_username ON users(username);
--rollback DROP INDEX idx_users_username;

--changeset dev:5 labels:release-1.0
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                            role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
                            PRIMARY KEY (user_id, role_id)
);
--rollback DROP TABLE user_roles;