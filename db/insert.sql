insert into roles(name) values ('programmer');

insert into users(name) values ('Ivan');
insert into users(name) values ('Tony');

insert into rules(name) values ('do a whiles');

insert into roles_rules(roles_id, rules_id) values (1, 1);

insert into states(name) values ('ready');
insert into states(name) values ('not ready');
insert into categories(name) values ('B1');

insert into items(name, items_users) values ('Pencil', 1);
insert into items(name, items_users) values ('Pen', 1);

insert into comments(name, comments_users) values ('Канцелярия огонь', 1);
insert into comments(name, comments_users) values ('Лайк продавцу', 1);

insert into attachs(name, items_attachs) values ('Ластик', 1);
insert into attachs(name, items_attachs) values ('Грифель', 1);

insert into items(name, items_categories) values ('Буклет', 1);
insert into items(name, items_categories) values ('Пластырь', 1);

insert into items(name, items_states) values ('Кирпич', 1);
insert into items(name, items_states) values ('Пластилин', 2);