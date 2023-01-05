use monster_bash;

insert into monster (monster_name, power, element, monster_image) values ('Lén', 47, 'fire', '/monsters_images/monster1');
insert into monster (monster_name, power, element, monster_image) values ('Naëlle', 5, 'water', '/monsters_images/monster2');
insert into monster (monster_name, power, element, monster_image) values ('Loïca', 58, 'air', '/monsters_images/monster3');
insert into monster (monster_name, power, element, monster_image) values ('Pål', 61, 'earth', '/monsters_images/monster4');
insert into monster (monster_name, power, element, monster_image) values ('Estève', 19, 'fire', '/monsters_images/monster5');
insert into monster (monster_name, power, element, monster_image) values ('Maéna', 41, 'water', '/monsters_images/monster6');
insert into monster (monster_name, power, element, monster_image) values ('Mélina', 12, 'air', '/monsters_images/monster7');
insert into monster (monster_name, power, element, monster_image) values ('Judicaël', 82, 'earth', '/monsters_images/monster8');
insert into monster (monster_name, power, element, monster_image) values ('Dorothée', 75, 'fire', '/monsters_images/monster9');
insert into monster (monster_name, power, element, monster_image) values ('Vishnu', 29, 'water', '/monsters_images/monster10');


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

insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Sword of Minor Static Electricity', 'desdev.cn', 65, 1, 1);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Staff of Soggyness', 'tripod.com', 60, 2, 2);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Mace of Too Many Rocks', 'yellowpages.com', 68, 3, 3);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Spear of Second Degree Burns','google.com', 70, 4, 4);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Bow and Arrow of Dangerously Icy Road Conditions', 'networksolutions.com', 58, 5, 5);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Spikey Shield of Wind Protection & Cowardice', 't.co', 17, 6, 6);

insert into monster_equipment (monster_equipment_id, monster_id, equipment_id) values(1,1,1);
insert into monster_equipment (monster_equipment_id, monster_id, equipment_id) values(2,2,2);
insert into monster_equipment (monster_equipment_id, monster_id, equipment_id) values(3,3,3);
insert into monster_equipment (monster_equipment_id, monster_id, equipment_id) values(4,4,4);
insert into monster_equipment (monster_equipment_id, monster_id, equipment_id) values(5,5,5);



insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Blizzard', 1, 5, 'intel.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Sandstorm', 2, 3, 'java.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Tornado', 3, 6, 'de.vu');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Flood', 4, 2, 'mozilla.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Electric Storm', 5, 1, 'meetup.com');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Sunshine', 6, 4, 'angelfire.com');


insert into location (location_name, location_id, element_id, location_image) values ('Minneapolis', 1, 1, 'drupal.org');
insert into location (location_name, location_id, element_id, location_image) values ('Las Vegas', 2, 2, 'cbc.ca');
insert into location (location_name, location_id, element_id, location_image) values ('Kansas', 3, 3, 'buzzfeed.com');
insert into location (location_name, location_id, element_id, location_image) values ('Seattle', 4, 4, 'ameblo.jp');
insert into location (location_name, location_id, element_id, location_image) values ('Houston', 5, 1, 'sfgate.com');
insert into location (location_name, location_id, element_id, location_image) values ('Miami', 6, 2, 'delicious.com');


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


