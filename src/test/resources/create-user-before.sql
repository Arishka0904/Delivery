delete from user_role;
delete from user;

insert into user (id, active, password, username) values
(1, true, '202cb962ac59075b964b07152d234b70', 'admin');

insert into user (id, active, password, username, email) values
(2, true, '202cb962ac59075b964b07152d234b70', 'User1', 'hena@fast-mail.one');


insert into user_role (user_id, roles) values
(1, 'ADMIN'), (1, 'USER'), (2, 'USER');