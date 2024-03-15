create
or replace procedure update_data(u_count integer, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count = 0 THEN
            delete from products
            where id = u_id;
        end if;
    END;
$$;

call update_data (0, 651)

create
or replace procedure delete_count(u_count integer)
language 'plpgsql'
as $$
    BEGIN
      	delete from products
     	where products.count = u_count;
    END;
$$;

call delete_count (14)

create
or replace function delete(u_count integer, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 10 THEN
            delete from products
            where id = u_id;
        end if;
        return result;
    end;
$$;

select delete (11, 654)