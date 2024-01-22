create table if not exists daily_update (
	id INT GENERATED ALWAYS AS IDENTITY,
	video_url text not null,
	created_at timestamp not null,
	last_updated_at timestamp not null,
	sender_id integer not null,
	primary key(id),
	constraint constraint_sender
    foreign key (sender_id) references postgres.employee(id)
);