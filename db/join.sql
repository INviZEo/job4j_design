create table departmens(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
 	deps_id int references departmens(id)
);

insert into departmens(name) values ('A1');
insert into departmens(name) values ('B1');
insert into departmens(name) values ('C1');
insert into departmens(name) values ('D1')
insert into departmens(name) values ('ААСС')

insert into employees(name, deps_id) values ('Виктор', 1);
insert into employees(name, deps_id) values ('Людмила', 1);
insert into employees(name, deps_id) values ('Андрей', 2);
insert into employees(name, deps_id) values ('Никита', 3);
insert into employees(name, deps_id) values ('Виталий', 3);
insert into employees(name, deps_id) values ('Ксения', 3);
insert into employees(name, deps_id) values ('Иван', 4);

select * from employees empl
left join departmens deps on empl.deps_id = deps.id

select * from employees empl
right join departmens deps on empl.deps_id = deps.id

select * from employees empl
full join departmens deps on empl.deps_id = deps.id

select * from employees empl
cross join departmens



select * from departmens deps
left join employees empl on empl.deps_id = deps.id



select * from employees empl
left join departmens deps on empl.deps_id = deps.id

select * from employees empl
right join departmens deps on empl.deps_id = deps.id




create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);



insert into teens(name, gender) values ('Виктор', 'Battle-helicopter');
insert into teens(name, gender) values ('Людмила', 'Female');
insert into teens(name, gender) values ('Андрей', 'Male');
insert into teens(name, gender) values ('Никита', 'Male');
insert into teens(name, gender) values ('Ксения', 'Female');
insert into teens(name, gender) values ('Иван', 'Male');



select n1.name as a, n2.name as b
from teens n1 cross join teens n2
where n1.gender = 'Male' and n2.gender = 'Female'