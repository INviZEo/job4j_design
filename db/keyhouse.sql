create table keyfromhouse(
	id serial primary key,
	key varchar(255)
);

create table humans(
	id serial primary key,
	name varchar(255),
	house_key int references keyfromhouse(id)
);

insert into keyfromhouse(key) values ('KeyFromMyHouse');

insert into humans(name, house_key) VALUES ('Serega', 1);

