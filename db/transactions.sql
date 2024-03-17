psql --username=postgres

\c unwrap

create table money_pepl(id serial primary key, name varchar(255), age int, money int);

insert into money_pepl (name, age, money) values ('Kostya', 18, 15000);
insert into money_pepl (name, age, money) values ('Andew', 25, 290000);
insert into money_pepl (name, age, money) values ('Kirill', 20, 1700);

set session transaction isolation level read uncommitted;

start transaction;

insert into money_pepl (name, age, money) values ('Krip', 25, 9999999);
delete from money_pepl where money = 1700;
update money_pepl set money = 1 where money = 9999999;


begin transaction;

insert into money_pepl (name, age, money) values ('Ded', 99, 100);

delete from money_pepl where name = 'Krip';

update money_pepl set money = 55000 where name = 'Andew';

begin transaction isolation level serializable;
insert into money_pepl (name, age, money) values ('Kirill', 20, 1700);
select sum(age) from money_pepl;
update money_pepl set age = 33 where name = 'Kostya';

select sum(age) from money_pepl;
update money_pepl set age = 33 where name = 'Andew';
commit;
