create table if not exists review (
	id INT GENERATED ALWAYS AS IDENTITY,
	video_url text not null,
	type varchar(32) not null,
	created_at timestamp not null,
	last_updated_at timestamp not null,
	receiver_id integer not null,
	sender_id integer not null,
	primary key(id),
	constraint constraint_receiver
	foreign key (receiver_id) references postgres.employee(id),
	constraint constraint_sender
    foreign key (sender_id) references postgres.employee(id)
);