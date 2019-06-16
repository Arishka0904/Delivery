insert into user (id, username, password, active, email)
values (1, 'admin', '123', true, 'arishka0904@gmail.com' );


insert into user_role (user_id, roles)
values (1, 'ADMIN');

insert into user (id, username, password, active, email)
values (2, 'user1', '1234', true, 'user1@gmail.com' ),
(3, 'user2', '12345', true, 'user2@gmail.com' );


insert into user_role (user_id, roles)
values (2, 'USER'), (3, 'USER');
