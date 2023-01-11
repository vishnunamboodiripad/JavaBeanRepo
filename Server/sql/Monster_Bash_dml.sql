use monster_bash;

insert into element (element_name, element_id, element_image) values ('Fire', 1, "google.com");
insert into element (element_name, element_id, element_image) values ('Water', 2, "google.com");
insert into element (element_name, element_id, element_image) values ('Earth', 3, "google.com");
insert into element (element_name, element_id, element_image) values ('Wind', 4, "google.com");

insert into affinity (affinity_name, affinity_image, affinity_id) values ('Electric',"google.com", 1);
insert into affinity (affinity_name, affinity_image, affinity_id) values ('Liquid',"google.com", 2);
insert into affinity (affinity_name, affinity_image, affinity_id) values ('Stone',"google.com", 3);
insert into affinity (affinity_name, affinity_image, affinity_id) values ('Flame',"google.com", 4);
insert into affinity (affinity_name, affinity_image, affinity_id) values ('Snow',"google.com", 5);
insert into affinity (affinity_name, affinity_image, affinity_id) values ('Breeze',"google.com", 6);

insert into monster (monster_name, power, element_id, monster_image) values ('Lén', 47, 1, 'https://app.pixelencounter.com/api/basic/monsters/10');
insert into monster (monster_name, power, element_id, monster_image) values ('Naëlle', 5, 2, 'https://app.pixelencounter.com/api/basic/monsters/22');
insert into monster (monster_name, power, element_id, monster_image) values ('Zach', 58, 3, 'https://app.pixelencounter.com/api/basic/monsters/85');
insert into monster (monster_name, power, element_id, monster_image) values ('Pål', 61, 4, 'https://app.pixelencounter.com/api/basic/monsters/434');
insert into monster (monster_name, power, element_id, monster_image) values ('Estève', 19, 1, 'https://app.pixelencounter.com/api/basic/monsters/75');
insert into monster (monster_name, power, element_id, monster_image) values ('Mike', 41, 2, 'https://app.pixelencounter.com/api/basic/monsters/400');
insert into monster (monster_name, power, element_id, monster_image) values ('Mélina', 12, 3, 'https://app.pixelencounter.com/api/basic/monsters/99');
insert into monster (monster_name, power, element_id, monster_image) values ('Judicaël', 82, 4, 'https://app.pixelencounter.com/api/basic/monsters/45');
insert into monster (monster_name, power, element_id, monster_image) values ('Dorothée', 75, 1, 'https://app.pixelencounter.com/api/basic/monsters/777');
insert into monster (monster_name, power, element_id, monster_image) values ('Vishnu', 29, 2, 'https://app.pixelencounter.com/api/basic/monsters/4');

insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Sword of Minor Static Electricity', 'https://media1.giphy.com/media/fgFKv7iL822GesuYNh/giphy.gif', 65, 1, 1);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Staff of Soggyness', 'https://media3.giphy.com/media/13JmE93YJMtjCL8jLg/giphy.gif', 60, 2, 2);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Mace of Too Many Rocks', 'https://media3.giphy.com/media/peDpV5AZOeSin6L5e8/giphy.gif', 68, 3, 3);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Spear of Second Degree Burns','https://media0.giphy.com/media/Qns2skrouXGi8sVKh6/giphy.gif', 70, 4, 4);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Bow and Arrow of Dangerously Icy Road Conditions', 'https://media4.giphy.com/media/4jBttKNeeux4Wd1Rrr/giphy.gif', 58, 5, 5);
insert into equipment (equipment_name, equipment_image, strength, affinity_id, equipment_id) values ('Spikey Shield of Wind Protection & Cowardice', 'https://media0.giphy.com/media/Q6jZduc5riol3Z6rGl/giphy.gif', 17, 6, 6);

insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Blizzard', 1, 5, 'https://www.publicdomainpictures.net/pictures/390000/velka/baum-nacht-schnee-winter-1613641634iKr.jpg');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Sandstorm', 2, 3, 'https://www.publicdomainpictures.net/pictures/210000/velka/hintergrund-tapete-1486984641pyy.jpg');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Tornado', 3, 6, 'https://www.publicdomainpictures.net/pictures/450000/velka/tornado-1651106114dvq.jpg');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Flood', 4, 2, 'https://www.publicdomainpictures.net/pictures/180000/velka/raging-river.jpg');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Electric Storm', 5, 1, 'https://www.publicdomainpictures.net/pictures/340000/velka/blitz-gewitter-sturm-wetter.jpg');
insert into weather (weather_name, weather_id, affinity_id, weather_image) values ('Sunshine', 6, 4, 'https://www.publicdomainpictures.net/pictures/310000/velka/wald-herbst-baume-laub-1573340070HKJ.jpg');


insert into location (location_name, location_id, element_id, location_image) values ('Minneapolis', 1, 1, 'https://i.imgur.com/75DaECA.png');
insert into location (location_name, location_id, element_id, location_image) values ('Forest', 2, 2, 'https://i.imgur.com/ezB2aRu.png');
insert into location (location_name, location_id, element_id, location_image) values ('Mountains', 3, 3, 'https://i.imgur.com/XJMHlWu.png');
insert into location (location_name, location_id, element_id, location_image) values ('Oceans', 4, 4, 'https://i.imgur.com/V9KdI2J.png');
insert into location (location_name, location_id, element_id, location_image) values ('Space', 5, 1, 'https://i.imgur.com/K3RvfbG.png');
insert into location (location_name, location_id, element_id, location_image) values ('Volcano', 6, 2, 'https://i.imgur.com/A7JDXoJ.png');

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

insert into battle ( player_monster_id, computer_monster_id, player_equipment_id, computer_equipment_id, weather_id, location_id, app_user_id, player_win) values ('1', '2', '1', '2', '1', '1', '1', true);

