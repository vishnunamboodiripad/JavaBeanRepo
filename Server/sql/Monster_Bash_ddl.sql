drop database if exists monster_bash;
create database monster_bash;
use monster_bash;

create table App_User (
	user_id int auto_increment primary key,
	username varchar(120) not null unique,
    `password` varchar(2048) not null,
    enabled bit not null 
);

create table App_Role (
	role_id int auto_increment primary key,
    role_name varchar(120) not null unique
);

create table App_Role_User (
	user_id int not null,
    role_id int not null,
    
    constraint foreign key (user_id) references App_User(user_id),
    constraint foreign key (role_id) references App_Role(role_id),
    constraint primary key (user_id, role_id)
);
##password for all users is 'password'
insert into App_User (user_id, username, password, enabled) values (1, 'bmadrell0', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (2, 'uomannion1', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (3, 'rcollinwood2', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (4, 'bbeange3', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (5, 'hsturzaker4', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (6, 'dchave5', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (7, 'sdhennin6', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (8, 'orown7', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (9, 'bkellie8', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);
insert into App_User (user_id, username, password, enabled) values (10, 'rbart9', '$2a$12$AqN/wwEwDhOTXSpL3.BhBe57Xg7AKJEoolkyqoAdaQVPYnnCq7GtO', true);

insert into App_Role (role_id, role_name) values (1, 'admin');
insert into App_Role (role_id, role_name) values (2, 'user');

insert into App_Role_User(role_id, user_id) values (1,1);
insert into App_Role_User(role_id, user_id) values (2,2);
insert into App_Role_User(role_id, user_id) values (2,3);
insert into App_Role_User(role_id, user_id) values (2,4);
insert into App_Role_User(role_id, user_id) values (2,5);
insert into App_Role_User(role_id, user_id) values (2,6);
insert into App_Role_User(role_id, user_id) values (2,7);
insert into App_Role_User(role_id, user_id) values (2,8);
insert into App_Role_User(role_id, user_id) values (2,9);
insert into App_Role_User(role_id, user_id) values (2,10);

