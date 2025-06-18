CREATE TABLE groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE students
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    group_id    INT     NOT NULL,
    is_attended BOOLEAN NOT NULL,

    CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES groups (id)
);

INSERT INTO groups (name)
VALUES ('Group A'),
       ('Group B'),
       ('Group C');


INSERT INTO students (name, group_id, is_attended)
VALUES ('Nurgali', 1, true),
       ('Zhansaya', 1, false),
       ('Aruzhan', 2, true),
       ('Yerassyl', 2, true),
       ('Dias', 3, false),
       ('Aisulu', 3, false),
       ('Altynbek', 1, true),
       ('Meruert', 2, true),
       ('Zharaskan', 3, false),
       ('Dana', 1, true);
