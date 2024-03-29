drop database if exists monster_bash_test;
create database monster_bash_test;
use monster_bash_test;

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

        

    
delimiter // 
create procedure set_known_good_state()
begin

delete from battle;
alter table battle auto_increment = 1;
delete from App_User;
alter table App_User auto_increment = 1;
delete from location;
alter table location auto_increment = 1;
delete from weather;
alter table weather auto_increment = 1;
delete from monster;
alter table monster auto_increment = 1;
delete from equipment;
alter table equipment auto_increment = 1;
delete from affinity;
alter table affinity auto_increment = 1;
delete from element;
alter table element auto_increment = 1;



insert into element (element_name, element_id) values ('Fire', 1);
insert into element (element_name, element_id) values ('Water', 2);
insert into element (element_name, element_id) values ('Earth', 3);
insert into element (element_name, element_id) values ('Wind', 4);

insert into affinity (affinity_name, affinity_id) values ('Electric', 1);
insert into affinity (affinity_name, affinity_id) values ('Liquid', 2);
insert into affinity (affinity_name, affinity_id) values ('Stone', 3);
insert into affinity (affinity_name, affinity_id) values ('Flame', 4);
insert into affinity (affinity_name, affinity_id) values ('Snow', 5);
insert into affinity (affinity_name, affinity_id) values ('Breeze', 6);

insert into monster (monster_name, power, element_id, monster_image) values ('Lén', 47, 1, '/monsters_images/monster1');
insert into monster (monster_name, power, element_id, monster_image) values ('Naëlle', 5, 2, '/monsters_images/monster2');
insert into monster (monster_name, power, element_id, monster_image) values ('Loïca', 58, 3, '/monsters_images/monster3');
insert into monster (monster_name, power, element_id, monster_image) values ('Pål', 61, 4, '/monsters_images/monster4');
insert into monster (monster_name, power, element_id, monster_image) values ('Estève', 19, 1, '/monsters_images/monster5');
insert into monster (monster_name, power, element_id, monster_image) values ('Maéna', 41, 2, '/monsters_images/monster6');
insert into monster (monster_name, power, element_id, monster_image) values ('Mélina', 12, 3, '/monsters_images/monster7');
insert into monster (monster_name, power, element_id, monster_image) values ('Judicaël', 82, 3, '/monsters_images/monster8');
insert into monster (monster_name, power, element_id, monster_image) values ('Dorothée', 75, 1, '/monsters_images/monster9');
insert into monster (monster_name, power, element_id, monster_image) values ('Vishnu', 29, 2, '/monsters_images/monster10');

insert into equipment (equipment_name, equipment_image, strength, affinity_id) values ('Sword of Minor Static Electricity', 'desdev.cn', 65, 1);
insert into equipment (equipment_name, equipment_image, strength, affinity_id) values ('Staff of Soggyness', 'tripod.com', 60, 2);
insert into equipment (equipment_name, equipment_image, strength, affinity_id) values ('Mace of Too Many Rocks', 'yellowpages.com', 68, 3);
insert into equipment (equipment_name, equipment_image, strength, affinity_id) values ('Spear of Second Degree Burns','google.com', 70, 4);
insert into equipment (equipment_name, equipment_image, strength, affinity_id) values ('Bow and Arrow of Dangerously Icy Road Conditions', 'networksolutions.com', 58, 5);
insert into equipment (equipment_name, equipment_image, strength, affinity_id) values ('Spikey Shield of Wind Protection & Cowardice', 't.co', 17, 5);

insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Blizzard', 1, 5, 'intel.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Sandstorm', 2, 3, 'java.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Tornado', 3, 5, 'de.vu');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Flood', 4, 2, 'mozilla.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Electric Storm', 5, 1, 'meetup.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Sunshine', 6, 4, 'angelfire.com');


insert into location (location_name, location_id, element_id, location_image) values ('Minneapolis', 1, 1, 'drupal.org');
insert into location (location_name, location_id, element_id, location_image) values ('Las Vegas', 2, 2, 'cbc.ca');
insert into location (location_name, location_id, element_id, location_image) values ('Kansas', 3, 3, 'buzzfeed.com');
insert into location (location_name, location_id, element_id, location_image) values ('Seattle', 4, 3, 'ameblo.jp');
insert into location (location_name, location_id, element_id, location_image) values ('Houston', 5, 1, 'sfgate.com');
insert into location (location_name, location_id, element_id, location_image) values ('Miami', 6, 2, 'delicious.com');

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

insert into battle (player_monster_id, computer_monster_id, player_equipment_id, computer_equipment_id, weather_id, location_id, app_user_id, player_win) 
values ('1', '2', '1', '2', '1', '1', '1', true);
end //
delimiter ;



    

