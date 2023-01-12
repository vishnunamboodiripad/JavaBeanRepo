drop database if exists monster_bash;
create database monster_bash;
use monster_bash;

drop table if exists monster;
drop table if exists equipment;
drop table if exists location;
drop table if exists weather;
drop table if exists affinity;
drop table if exists monster_equipment;
drop table if exists element;
drop table if exists battle;


create table element (
    element_id int primary key auto_increment,
    element_image varchar(1636),
    element_name varchar(10) not null
    );
    
create table location (
    location_id int primary key auto_increment,
    location_name varchar (50) not null,
    location_image varchar(1636) not null,
    element_id int not null,
        constraint fk_element_location_id
        foreign key (element_id)
        references element(element_id)
    );
    
create table affinity (
    affinity_id int primary key auto_increment,
    affinity_image varchar(100),
    affinity_name varchar(15) not null
    );

create table weather (
    weather_id int primary key auto_increment,
    weather_name varchar(15) unique not null,
    weather_image varchar(1636) not null,
    affinity_id int null,
        constraint fk_affinity_weather_id
        foreign key (affinity_id)
        references affinity(affinity_id)
        );



create table equipment (
    equipment_id int primary key auto_increment,
    equipment_name varchar(500) not null,
    equipment_image varchar(1636) not null,
    strength int not null,
    affinity_id int not null,
    constraint fk_affinity_equipment_id
        foreign key (affinity_id)
        references affinity(affinity_id)
        );
create table monster (
    monster_id int primary key auto_increment,
    monster_name varchar(25) unique not null,
    monster_image varchar(1636) not null,
    power int not null ,
    element_id int not null,
        constraint fk_element_monster_id
        foreign key (element_id)
        references element(element_id)
);
        
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



create table battle (
	battle_id int primary key auto_increment,
	player_monster_id int not null,
		constraint fk_monster_battle_id_1
		foreign key (player_monster_id)
		references monster(monster_id),
	computer_monster_id int not null,
		constraint fk_monster_battle_id_2
		foreign key (computer_monster_id)
		references monster(monster_id),
	player_equipment_id int not null,
		constraint fk_equipment_battle_id_1
		foreign key (player_equipment_id)
		references equipment(equipment_id),
	computer_equipment_id int not null,
		constraint fk_equipment_battle_id_2
		foreign key (computer_equipment_id)
		references equipment(equipment_id),
	weather_id int not null,
		constraint fk_weather_battle_id
		foreign key (weather_id)
		references weather(weather_id),
	location_id int not null,
		constraint fk_location_battle_id
		foreign key (location_id)
		references location(location_id),
	app_user_id int not null,
		constraint fk_app_user_battle_id
		foreign key (app_user_id)
        references App_User(user_id),
	player_win boolean not null
    );
