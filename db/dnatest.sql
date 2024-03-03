create table dna(
	id serial primary key,
	dnaof varchar(255)
);

create table people(
	id serial primary key,
	name varchar(255)
);

create table dna_people(
	id serial primary key,
	dna_id int references dna(id) unique,
	people_id int references people(id) unique
);
