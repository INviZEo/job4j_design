create table peoples(
    id serial primaty key,
    name varchar(255),
    surname text,
    age int
);

insert into peoples(name, surname, age) values('Tosha', 'Hachapurev', 28);

update peoples set age = 25;

delete from peoples;