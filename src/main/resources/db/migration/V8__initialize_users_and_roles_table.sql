CREATE TABLE users (
    user_id UUID PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE, 
    password TEXT NOT NULL
);

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role_id SERIAL NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_RECRUITER');
INSERT INTO roles (name) VALUES ('ROLE_CANDIDATE');
INSERT INTO roles (name) VALUES ('ROLE_GUEST');