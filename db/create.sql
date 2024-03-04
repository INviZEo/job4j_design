create table roles(
	id serial primary key,
	name text
);

create table states(
	id serial primary key,
	name text
);

create table categories(
	id serial primary key,
	name text
);

create table users(
 	id serial primary key,
	name text,
	roles_users int references roles(id)
);

create table rules(
	id serial primary key,
	name text
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table items(
	id serial primary key,
	name text,
	items_users int references users(id),
	items_states int references states(id),
	items_categories int references categories(id)
);

create table comments(
	id serial primary key,
	name text,
	comments_users int references items(id)
);

create table attachs(
	id serial primary key,
	name text,
	items_attachs int references items(id)
);