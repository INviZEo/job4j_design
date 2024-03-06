create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

select * from fauna where name like 'fish';
select * from fauna where avg_age > 9999 and avg_age < 21001;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';