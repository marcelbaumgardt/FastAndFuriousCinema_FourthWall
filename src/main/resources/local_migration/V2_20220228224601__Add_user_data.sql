insert into public.users (id, username, password, enabled)
values (1, 'admin', '{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);

insert into public.authorities (username, authority) values ('admin','ADMIN');


insert into public.users (id, username, password, enabled)
values (2   , 'user', '{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);

insert into public.authorities (username, authority) values ('user','USER');