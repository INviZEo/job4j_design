CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);



insert into customers (first_name, last_name, age, country) values ('Dmitry', 'Xleb', 27, 'Russia');
insert into customers (first_name, last_name, age, country) values ('Gans', 'Hansn', 33, 'Germany');
insert into customers (first_name, last_name, age, country) values ('Thomas', 'Soyer', 21, 'England');



select first_name, min(age) from customers
group by first_name
having min(age) = (select min(age) from customers);



CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);



insert into orders (amount, customer_id) values (0, 1);
insert into orders (amount, customer_id) values (5, 2);
insert into orders (amount, customer_id) values (1, 3);
insert into orders (amount, customer_id) values (0, 4);

select * from orders where customer_id not in (select customer_id from orders where amount != 0)

--»À»

select c.first_name from orders
join customers as c on orders.customer_id = c.id
where customer_id not in (select customer_id from orders where amount != 0)

--»À»

select first_name from customers
where customers.id not in (select customer_id from orders where amount != 0)