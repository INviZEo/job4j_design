create table bankid(
	id serial primary key,
	bankname varchar(255)
);

create table humans(
	id serial primary key,
	name varchar(255)
);

create table banks_humans(
	id serial primary key,
	bank_id int references bankid(id),
	humans_id int references humans(id)
);

insert into humans(name) VALUES ('Serega');
insert into humans(name) VALUES ('Gorc');

insert into bankid(bankname) values ('Tink');
insert into bankid(bankname) values ('Sebrbank');

insert into banks_humans(humans_id, bank_id) values (1,2);
insert into banks_humans(humans_id, bank_id) values (2,2);
insert into banks_humans(humans_id, bank_id) values (2,1);