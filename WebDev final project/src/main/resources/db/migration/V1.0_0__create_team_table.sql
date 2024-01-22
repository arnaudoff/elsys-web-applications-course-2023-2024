create table if not exists team (
    id INT GENERATED ALWAYS AS IDENTITY,
    team_name varchar(32) not null,
    primary key(id)
);