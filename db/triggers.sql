create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);


create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();


drop trigger tax_trigger on products



create
or replace function tax()
    returns trigger as
$$
    BEGIN
		new.price = new.price * 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger tax_trigger
    before insert
    on products
    for each row
    execute procedure tax();



create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);


drop trigger tax_trigger on products



create
or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values (new.name, new.price, current_date);
        return new;
    END;
$$
LANGUAGE 'plpgsql';



create trigger price_history
    before insert
    on products
    for each row
    execute procedure history();