DROP SCHEMA IF EXISTS saro cascade;
CREATE SCHEMA saro;

CREATE TABLE saro.todolist (
    todo_id uuid,
    todo varchar(300),
    created_date TIMESTAMP WITH TIME ZONE,
    modified_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (todo_id)
);
