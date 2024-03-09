create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
	price int
);

insert into type(name) values ('���')
insert into type(name) values ('����������')
insert into type(name) values ('������')


insert into product(name, type_id, expired_date, price)
values ('�����', 1, '21.09.2022', 15000)

insert into product(name, type_id, expired_date, price)
values ('���������', 1, '02.05.2022', 1000)

insert into product(name, type_id, expired_date, price)
values ('�������', 2, '01.05.2023', 288)

insert into product(name, type_id, expired_date, price)
values ('�����', 3, '02.05.2023', 150)



select * from product join type on product.type_id = type_id;

select * from product where name like '%����������%';

select * from product where expired_date > '21.09.2022';

select s.name, max(ss.price)
from product as ss
join type s on ss.type_id = s.id
group by s.id

select t.name,
count (pr.type_id) type_count
from product pr
join type t on t.id = pr.type_id
group by t.name

select t.name,
count (pr.type_id) type_count
from product pr
join type t on t.id = pr.type_id
group by t.name having t.name like '���' or t.name like '������'

select t.name,
count (pr.type_id) type_count
from product pr
join type t on t.id = pr.type_id
group by t.name having count (pr.type_id) > 10

select pr.name, t.name
from product pr
join type t on t.id = pr.type_id
group by pr.name, t.name