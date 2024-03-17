start transaction;

savepoint one;

delete from money_pepl where id = 6;

rollback to savepoint one;

savepoint two;

delete from money_pepl;

select * from money_pepl;

drop table money_pepl;

rollback to savepoint two;

drop table money_pepl;

rollback to savepoint one;