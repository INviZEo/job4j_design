create table car_bodies(
	id serial primary key,
	name varchar(255)
);

create table car_engines(
	id serial primary key,
	name varchar(255)
);

create table car_transmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);



insert into car_bodies(name) values ('хетчбек');
insert into car_bodies(name) values ('седан');
insert into car_bodies(name) values ('пикап');
insert into car_bodies(name) values ('заниженная');

insert into car_engines(name) values ('1000L');
insert into car_engines(name) values ('2000L');
insert into car_engines(name) values ('3000L');
insert into car_engines(name) values ('4000L');

insert into car_transmissions(name) values ('A1');
insert into car_transmissions(name) values ('B2');
insert into car_transmissions(name) values ('C3');
insert into car_transmissions(name) values ('R4');

insert into cars(name, body_id, engine_id, transmission_id) values ('Lambo', 2, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Nissan', 1, 2, 3);
insert into cars(name, body_id, engine_id) values ('BMW', 1, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('LADA', 3, 3, 3);

select crs.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name
from cars as crs
left join car_bodies cb on cb.id = crs.body_id
left join car_engines ce on ce.id = crs.engine_id
left join car_transmissions ct on ct.id = crs.transmission_id
group by crs.name, cb.name, ce.name, ct.name having ct.name is null


create view show_car_nonusing_transmisson
as
select crs.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name
from cars as crs
left join car_bodies cb on cb.id = crs.body_id
left join car_engines ce on ce.id = crs.engine_id
left join car_transmissions ct on ct.id = crs.transmission_id
group by crs.name, cb.name, ce.name, ct.name having ct.name is null

select * from show_car_nonusing_transmisson