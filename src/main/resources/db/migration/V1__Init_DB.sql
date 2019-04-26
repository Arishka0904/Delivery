
create table product (
    id bigint not null AUTO_INCREMENT,
    product_name varchar(255) not null,
    category varchar(255) not null,
    price DECIMAL(10,2) not null,
    depth integer not null,
    height integer not null,
    weight integer not null,
    width integer not null,
    quantity_on_pallet integer not null,
    quantity_in_warehouse integer,
    current_version bit,
    product_status integer,
    primary key (id)) engine=MyISAM;

create table user (
    id bigint not null AUTO_INCREMENT,
    user_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)) engine=MyISAM;

create table user_role (
    user_id bigint not null,
    roles varchar(255)) engine=MyISAM;

alter table user_role
    add constraint user_role_fk
    foreign key (user_id) references user (id);

create table orders (
    id bigint not null AUTO_INCREMENT,
    user_id bigint not null,
    address varchar(255) not null,
    order_amount DECIMAL(10,2) not null,
    status integer not null,
    date_created timestamp,
    last_updated timestamp,
    version integer,
    primary key (id)) engine=MyISAM;

alter table orders
    add constraint user_fk
    foreign key (user_id) references user (id);

create table product_in_order (
     id bigint not null AUTO_INCREMENT,
     order_id bigint not null,
     product_id bigint not null,
     quantity integer not null,
     date_created timestamp,
     last_updated timestamp,
     version integer,
   primary key (id)) engine=MyISAM;

alter table product_in_order
     add constraint order_fk
     foreign key (order_id) references orders (id);

alter table product_in_order
     add constraint product_fk
     foreign key (product_id) references product (id);
