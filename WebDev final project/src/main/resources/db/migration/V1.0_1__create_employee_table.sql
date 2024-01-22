create table if not exists employee (
	id INT GENERATED ALWAYS AS IDENTITY,
	first_name varchar(32) not null,
	last_name varchar(32) not null,
	email varchar(32) not null,
	picture_url text,
	position varchar(32) not null,
	team_id integer not null,
	primary key(id),
	constraint constraint_team
	foreign key (team_id) references postgres.team(id)
);