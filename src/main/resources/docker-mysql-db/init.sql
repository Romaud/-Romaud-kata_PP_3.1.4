insert into roles
values (2, 'ROLE_ADMIN');
insert into roles
values (1, 'ROLE_USER');

insert into users
values (2, 28, 'root@root.com', '$2a$10$o4DLqARubf3x/i9hKlkJ0.Wl3Tgvi99Hj8dUboLZ76PCpWEsNiaCC', 'root', 'root');

insert into users
values (1, 28, 'admin@mail.ru', '$2a$10$bcP0Oxj34Hv6fUHswz8nqOoDilILU.v0.Mz42KJZqc1Y3LeguxUUC', 'admin', 'admin') ;

insert into users_roles values (1, 1);
insert into users_roles values (2, 1);
insert into users_roles values (1, 2);
insert into users_roles values (2, 2);