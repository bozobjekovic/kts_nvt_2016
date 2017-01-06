select * from location;
select * from user;
select * from advertisment;
select * from real_estate;
select * from authority;
select * from admin;
select * from verifier;
select * from company;
select * from inappropriate;
select * from comment;
select * from rent_real_estate;

drop table Admin;
drop table Comment;
drop table Company;
drop table Inappropriate;
drop table Advertisment;
drop table User;
drop table Real_estate;
drop table Verifier;
drop table Location;
drop table Authority;
drop table rent_real_estate;

set foreign_key_checks = 0;

truncate Authority;
truncate Admin;
truncate Comment;
truncate Inappropriate;
truncate Company;
truncate Advertisment;
truncate User;
truncate Real_estate;
truncate Verifier;
truncate Location;
truncate rent_real_estate;

set foreign_key_checks = 1;

insert into authority (name) values ('ADMIN');
insert into authority (name) values ('USER');
insert into authority (name) values ('VERIFIER');

INSERT INTO User VALUES (1, 'Balzakova', 'Novi Sad', 'user@gmail.com', 'image', 'User', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+4129494', 'Useran', 'user', '52389028', false, false, 2, 5, 2, null, null);
INSERT INTO User VALUES (2, 'Niska', 'Nis', 'user2@gmail.com', 'image', 'User2', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+523525', 'Useran2', 'user2', '646546', false, false, 3, 2, 2, null, null);
INSERT INTO User VALUES (3, 'Kragujevacka', 'Kragujevac', 'user3@gmail.com', 'image', 'User3', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+3646363', 'Useran3', 'user3', '32525', false, false, 2, 2, 2, null, null);

INSERT INTO Location VALUES (1, 'Novi Sad', 'Liman', 21000);
INSERT INTO Location VALUES (2, 'Beograd', 'Kosutnjak', 11000);
INSERT INTO Location VALUES (3, 'Novi Sad', 'Bulevar', 21000);
INSERT INTO Location VALUES (4, 'Nis', 'Deo Nisa', 12000);
INSERT INTO Location VALUES (5, 'Beograd', 'Zemun', 23560);
INSERT INTO Location VALUES (6, 'Kragujevac', 'Deo Kragujevca', 13000);
INSERT INTO Location VALUES (7, 'Smederevo', 'Deo Smedereva', 14000);
INSERT INTO Location VALUES (8, 'Nis', 'Deo Nisa 1', 12000);
INSERT INTO Location VALUES (9, 'Novi Sad', 'Detelinara', 21000);
INSERT INTO Location VALUES (10, 'Novi Sad', 'Petrovaradin', 21000);
INSERT INTO Location VALUES (11, 'Novi Sad', 'Podbara', 21000);
INSERT INTO Location VALUES (12, 'Novi Sad', 'Grbavica', 21000);

INSERT INTO Location VALUES (13, 'Novi Sad', '', 21000);
INSERT INTO Location VALUES (14, 'Beograd', '', 11000);
INSERT INTO Location VALUES (15, 'Nis', '', 12000);
INSERT INTO Location VALUES (16, 'Kragujevac', '', 13000);
INSERT INTO Location VALUES (17, 'Smederevo', '', 14000);

INSERT INTO Admin VALUES (1, 'Balzakova', 'Novi Sad', 'admin@gmail.com', 'image', 'Admin', 'a', '+381412941', 'Adminovski', 'admin', 1);

INSERT INTO Verifier VALUES (1, 'verifier@gmail.com', '$2a$04$TjNk.I2AJ/QGBZKyOO6uruAx9MRoHsYKTJHcTd35rmmULDoM/XiXy','Verifier', 3);
INSERT INTO Verifier VALUES (2, 'verifier2@gmail.com','$2a$04$TjNk.I2AJ/QGBZKyOO6uruAx9MRoHsYKTJHcTd35rmmULDoM/XiXy', 'Verifier2', 3);

INSERT INTO Company VALUES (1, 'Balzakova 6', 'Kompanija Neka', '+3819214814', 'www.kompanijaneka.com', 1);
INSERT INTO Company VALUES (2, 'Bul. Mihaila Pupina', 'Nasa Kompanija', '+38142342', 'www.nasakompanija.com', 2);
INSERT INTO Company VALUES (3, 'Bulevar Oslobodjenja', 'Company', '+38164564', 'www.comany.com', 3);
INSERT INTO Company VALUES (4, 'Miselinova', 'Kompanija DOO', '+3816456456', 'www.kompanijadoo.com', 4);

INSERT INTO User VALUES (4, 'Balzakova', 'Novi Sad', 'clerk@gmail.com', 'image', 'Clerk', '$2a$06$ESTR0aRvH5s4McmiWeiXl.DqRxveC/lruQKFD7UVkvBRwOEdp2Qty', '+12331203', 'Clerkan', 'clerk', '21345411', false, true, 1, 2, 2, null, 1);
INSERT INTO User VALUES (5, 'Novosadska', 'Novi Sad', 'clerk2@gmail.com', 'image', 'Clerk2', '$2a$06$ESTR0aRvH5s4McmiWeiXl.DqRxveC/lruQKFD7UVkvBRwOEdp2Qty', '+15232', 'Clerkan2', 'clerk2', '9678678', true, true, 2, 2, 2, null, 2);

INSERT INTO Real_Estate VALUES (1, 'Balzakova 6', 1890, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Apartment', 1);
INSERT INTO Real_Estate VALUES (2, 'Bul. Mihaila Pupina', 1950, 0, 'radijatori', 100, 3, 5, 4, 0, 'TV,Klima,Namestak', 'Room', 4);
INSERT INTO Real_Estate VALUES (3, 'Bul. Mihaila Pupina', 1960, 0, 'radijatori', 52, 2, 2, 2, 1, 'TV,Klima,Bazen', 'House', 3);
INSERT INTO Real_Estate VALUES (4, 'Bulevar Oslobodjenja', 1994, 0, 'radijatori', 3506, 2, 3, 2, 0, 'Nista', 'Apartment', 4);
INSERT INTO Real_Estate VALUES (5, 'Miselinova', 1994, 1, 'radijatori', 3506, 0, 3, 2, 0, 'Nista', 'House', 4);

INSERT INTO Real_Estate VALUES (6, 'Balzakova 6', 1990, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Office', 1);
INSERT INTO Real_Estate VALUES (7, 'Safarikova 6', 2000, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Warehouse', 2);
INSERT INTO Real_Estate VALUES (8, 'Brace Dronjak 6', 2006, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Warehouse', 3);
INSERT INTO Real_Estate VALUES (9, 'Balzakova 16', 2009, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Apartment', 1);
INSERT INTO Real_Estate VALUES (10, 'Bulevar Oslobodjenja 6', 2015, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'House', 2);
INSERT INTO Real_Estate VALUES (11, 'Bulevar cara Lazara 6', 2006, 2, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Building Plot', 3);
INSERT INTO Real_Estate VALUES (12, 'Mose Pijade 6', 1994, 2, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Agricultural Plot', 1);
INSERT INTO Real_Estate VALUES (13, 'Balzakova 16', 1990, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Office', 1);
INSERT INTO Real_Estate VALUES (14, 'Safarikova 16', 2000, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Warehouse', 2);
INSERT INTO Real_Estate VALUES (15, 'Brace Dronjak 16', 2006, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Warehouse', 3);
INSERT INTO Real_Estate VALUES (16, 'Balzakova 116', 2009, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Apartment', 1);
INSERT INTO Real_Estate VALUES (17, 'Bulevar Oslobodjenja 16', 2015, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'House', 2);
INSERT INTO Real_Estate VALUES (18, 'Bulevar cara Lazara 16', 2006, 2, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Building Plot', 3);
INSERT INTO Real_Estate VALUES (19, 'Mose Pijade 16', 1994, 2, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Agricultural Plot', 1);
INSERT INTO Real_Estate VALUES (20, 'Balzakova 26', 1990, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Office', 1);
INSERT INTO Real_Estate VALUES (21, 'Safarikova 26', 2000, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Warehouse', 2);
INSERT INTO Real_Estate VALUES (22, 'Brace Dronjak 26', 2006, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Warehouse', 3);
INSERT INTO Real_Estate VALUES (23, 'Balzakova 126', 2009, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Apartment', 1);
INSERT INTO Real_Estate VALUES (24, 'Bulevar Oslobodjenja 26', 2015, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'House', 2);
INSERT INTO Real_Estate VALUES (25, 'Bulevar cara Lazara 26', 2006, 2, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Building Plot', 3);
INSERT INTO Real_Estate VALUES (26, 'Mose Pijade 26', 1994, 2, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Agricultural Plot', 1);

INSERT INTO Real_Estate VALUES (27, 'Bulevar Oslobodjenja 36', 2015, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Room', 2);
INSERT INTO Real_Estate VALUES (28, 'Bulevar cara Lazara 36', 2006, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Manufacturing Facility', 3);
INSERT INTO Real_Estate VALUES (29, 'Mose Pijade 36', 1994, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Manufacturing Facility', 1);
INSERT INTO Real_Estate VALUES (30, 'Balzakova 36', 1990, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Hospitality Facility', 1);
INSERT INTO Real_Estate VALUES (31, 'Safarikova 36', 2000, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Sport Facility', 2);
INSERT INTO Real_Estate VALUES (32, 'Brace Dronjak 36', 2006, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Manufacturing Facility', 3);
INSERT INTO Real_Estate VALUES (33, 'Balzakova 136', 2009, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Room', 1);
INSERT INTO Real_Estate VALUES (34, 'Bulevar Oslobodjenja 36', 2015, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Garage', 6);
INSERT INTO Real_Estate VALUES (35, 'Bulevar cara Lazara 36', 2006, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Hospitality Facility', 3);
INSERT INTO Real_Estate VALUES (36, 'Mose Pijade 36', 1994, 1, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Manufacturing Facility', 1);

INSERT INTO Advertisment VALUES (1, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 10, '+481924842', 234412, '2017-12-20', 'sell', '4', null, 4, 2, 1);
INSERT INTO Advertisment VALUES (2, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 11,'+3453535', 43242,'2017-12-20', 'sell', '5', null, 2, 1, 2);
INSERT INTO Advertisment VALUES (3, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+52432342', 423424, '2017-12-20', 'rent', '3.2', null, 3, 4, 2);
INSERT INTO Advertisment VALUES (4, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+5243234982', 423424, '2017-12-20', 'rent', '3.2', null, 1, 3, 1);
INSERT INTO Advertisment VALUES (5, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Kuca velika', 10, '+48192482', 234412, '2017-12-20', 'sell', '4', null, 2, 2, 1);
INSERT INTO Advertisment VALUES (6, '2016-12-20', 'images/house3.jpg', 0, '2016-12-23', 'Stan na brdu', 11,'+345535', 43242,'2017-12-20', 'sell', '5', null, 3, 1, 2);
INSERT INTO Advertisment VALUES (7, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 55,'+5243342', 423424, '2017-12-20', 'rent', '3.2', null, 1, 4, 2);
INSERT INTO Advertisment VALUES (8, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 4,'+52434982', 423424, '2017-12-20', 'rent', '3.2', null, 3, 5, 1);
INSERT INTO Advertisment VALUES (9, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 90, '+481982', 234412, '2017-12-20', 'sell', '4', null, 1, 2, 1);
INSERT INTO Advertisment VALUES (10, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 14,'+34577535', 43242,'2017-12-20', 'sell', '5', null, null, 3, 2);
INSERT INTO Advertisment VALUES (11, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 4,'+5243232', 423424, '2017-12-20', 'rent', '3.2', null, 2, 4, 2);
INSERT INTO Advertisment VALUES (12, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 7,'+5234982', 423424, '2017-12-20', 'rent', '3.2', null, 3, 5, 2);
INSERT INTO Advertisment VALUES (13, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 18, '+4924842', 234412, '2017-12-20', 'sell', '4', null, 1, 2, 1);
INSERT INTO Advertisment VALUES (14, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 11,'+3453577', 43242,'2017-12-20', 'sell', '5', null, 2, 1, 2);
INSERT INTO Advertisment VALUES (15, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+532342', 4234240, '2017-12-20', 'rent', '3.2', null, 3, 4, 2);
INSERT INTO Advertisment VALUES (16, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+5243454982', 4203424, '2017-12-20', 'rent', '3.2', null, 1, 5, 1);
INSERT INTO Advertisment VALUES (17, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+492481142', 2340412, '2017-12-20', 'rent', '4', null, 1, 6, 1);
INSERT INTO Advertisment VALUES (18, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+34535717', 34013242,'2017-12-20', 'rent', '5', null, 2, 7, 2);
INSERT INTO Advertisment VALUES (19, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+53222342', 20423424, '2017-12-20', 'rent', '3.2', null, 3, 8, 2);
INSERT INTO Advertisment VALUES (20, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+52434542982', 11423424, '2017-12-20', 'rent', '3.2', null, 1, 9, 1);
INSERT INTO Advertisment VALUES (21, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+5323402', 42340124, '2017-12-20', 'rent', '3.2', null, 3, 10, 2);
INSERT INTO Advertisment VALUES (22, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+504982', 42340124, '2017-12-20', 'rent', '3.2', null, 1, 11, 2);
INSERT INTO Advertisment VALUES (23, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+49240142', 23014412, '2017-12-20', 'rent', '4', null, 1, 12, 1);
INSERT INTO Advertisment VALUES (24, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+34535707', 3430242,'2017-12-20', 'rent', '5', null, 2, 13, 2);
INSERT INTO Advertisment VALUES (25, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+532022342', 2423000424, '2017-12-20', 'rent', '3.2', null, 3, 14, 2);
INSERT INTO Advertisment VALUES (26, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+5243450', 14230424, '2017-12-20', 'rent', '3.2', null, 1, 15, 1);
INSERT INTO Advertisment VALUES (27, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+41232', 2340412, '2017-12-20', 'rent', '4', null, 1, 16, 1);
INSERT INTO Advertisment VALUES (28, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+34533417', 34013242,'2017-12-20', 'rent', '5', null, 2, 17, 2);
INSERT INTO Advertisment VALUES (29, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+5321112', 20423424, '2017-12-20', 'rent', '3.2', null, 3, 18, 2);
INSERT INTO Advertisment VALUES (30, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+1142982', 11423424, '2017-12-20', 'rent', '3.2', null, 1, 19, 1);
INSERT INTO Advertisment VALUES (31, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+122234502', 42340124, '2017-12-20', 'rent', '3.2', null, 3, 20, 2);
INSERT INTO Advertisment VALUES (32, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+50492282', 42340124, '2017-12-20', 'rent', '3.2', null, 1, 21, 2);
INSERT INTO Advertisment VALUES (33, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+4924021142', 23014412, '2017-12-20', 'rent', '4', null, 1, 22, 1);
INSERT INTO Advertisment VALUES (34, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+3453521707', 3430242,'2017-12-20', 'rent', '5', null, 2, 23, 2);
INSERT INTO Advertisment VALUES (35, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+53202112342', 2423000424, '2017-12-20', 'rent', '3.2', null, 3, 24, 2);
INSERT INTO Advertisment VALUES (36, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+524111450', 14230424, '2017-12-20', 'rent', '3.2', null, 1, 25, 1);
INSERT INTO Advertisment VALUES (37, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+41244432', 2340412, '2017-12-20', 'rent', '4', null, 1, 26, 1);
INSERT INTO Advertisment VALUES (38, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+345334417', 34013242,'2017-12-20', 'rent', '5', null, 2, 17, 2);
INSERT INTO Advertisment VALUES (39, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+532144112', 20423424, '2017-12-20', 'rent', '3.2', null, 2, 18, 2);
INSERT INTO Advertisment VALUES (40, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+114294482', 11423424, '2017-12-20', 'rent', '3.2', null, 1, 19, 1);
INSERT INTO Advertisment VALUES (41, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+1244434502', 42340124, '2017-12-20', 'rent', '3.2', null, 3, 20, 2);
INSERT INTO Advertisment VALUES (42, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+5044444282', 42340124, '2017-12-20', 'rent', '3.2', null, 1, 21, 1);
INSERT INTO Advertisment VALUES (43, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+492404142', 23014412, '2017-12-20', 'rent', '4', null, 1, 22, 1);
INSERT INTO Advertisment VALUES (44, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+34441707', 3430242,'2017-12-20', 'rent', '5', null, 2, 23, 2);
INSERT INTO Advertisment VALUES (45, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+5442342', 2423000424, '2017-12-20', 'rent', '3.2', null, 3, 24, 2);
INSERT INTO Advertisment VALUES (46, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+52414440', 14230424, '2017-12-20', 'rent', '3.2', null, 1, 25, 1);

INSERT INTO Advertisment VALUES (47, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+412444320', 2340412, '2017-12-20', 'rent', '4', null, 1, 36, 1);
INSERT INTO Advertisment VALUES (48, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+3453344170', 34013242,'2017-12-20', 'rent', '5', null, 2, 27, 2);
INSERT INTO Advertisment VALUES (49, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+5321441120', 20423424, '2017-12-20', 'rent', '3.2', null, 2, 28, 2);
INSERT INTO Advertisment VALUES (50, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+1142944820', 11423424, '2017-12-20', 'rent', '3.2', null, 1, 29, 1);
INSERT INTO Advertisment VALUES (51, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+12444345020', 42340124, '2017-12-20', 'rent', '3.2', null, 3, 30, 2);
INSERT INTO Advertisment VALUES (52, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+50444442820', 42340124, '2017-12-20', 'rent', '3.2', null, 1, 31, 1);
INSERT INTO Advertisment VALUES (53, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+4924041420', 23014412, '2017-12-20', 'rent', '4', null, 1, 32, 1);
INSERT INTO Advertisment VALUES (54, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+344417070', 3430242,'2017-12-20', 'rent', '5', null, 2, 33, 2);
INSERT INTO Advertisment VALUES (55, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+54423420', 2423000424, '2017-12-20', 'rent', '3.2', null, 3, 34, 2);
INSERT INTO Advertisment VALUES (56, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+524144400', 14230424, '2017-12-20', 'rent', '3.2', null, 1, 35, 1);

INSERT INTO Advertisment VALUES (57, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+4124320', 2340412, '2017-12-20', 'sell', '4', null, 1, 36, 1);
INSERT INTO Advertisment VALUES (58, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+34533440', 34013242,'2017-12-20', 'sell', '5', null, 2, 27, 2);
INSERT INTO Advertisment VALUES (59, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+53241120', 20423424, '2017-12-20', 'sell', '3.2', null, 2, 28, 2);
INSERT INTO Advertisment VALUES (60, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+11944820', 11423424, '2017-12-20', 'sell', '3.2', null, 1, 29, 1);
INSERT INTO Advertisment VALUES (61, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+124443020', 42340124, '2017-12-20', 'sell', '3.2', null, 3, 30, 2);
INSERT INTO Advertisment VALUES (62, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+504444820', 42340124, '2017-12-20', 'sell', '3.2', null, 1, 31, 1);
INSERT INTO Advertisment VALUES (63, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+49241420', 23014412, '2017-12-20', 'sell', '4', null, 1, 32, 1);
INSERT INTO Advertisment VALUES (64, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+3447070', 3430242,'2017-12-20', 'sell', '5', null, 2, 33, 2);
INSERT INTO Advertisment VALUES (65, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+5443420', 2423000424, '2017-12-20', 'sell', '3.2', null, 3, 34, 2);
INSERT INTO Advertisment VALUES (66, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+52414400', 14230424, '2017-12-20', 'sell', '3.2', null, 1, 35, 1);
INSERT INTO Advertisment VALUES (67, '2016-12-20', 'images/house1.jpg', 0, '2016-12-23', 'Kuca velika', 17, '+492420', 23014412, '2017-12-20', 'sell', '4', null, 1, 32, 1);
INSERT INTO Advertisment VALUES (68, '2016-12-20', 'images/house2.jpg', 0, '2016-12-23', 'Stan na brdu', 12,'+37070', 3430242,'2017-12-20', 'sell', '5', null, 2, 33, 2);
INSERT INTO Advertisment VALUES (69, '2016-12-20', 'images/house3.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 49,'+54430', 2423000424, '2017-12-20', 'sell', '3.2', null, 3, 34, 2);
INSERT INTO Advertisment VALUES (70, '2016-12-20', 'images/house4.jpg', 0,'2016-12-23',  'Kuca sa bazenom', 48,'+524400', 14230424, '2017-12-20', 'sell', '3.2', null, 1, 35, 1);

INSERT INTO Inappropriate VALUES(1, '2017-12-20',"Inappropriate advertisement", "Inappropriate", 1, 1);
INSERT INTO Inappropriate VALUES(2, '2017-12-20',"Inappropriate advertisement", "Inappropriate", 1, 2);
INSERT INTO Inappropriate VALUES(3, '2017-12-20',"Inappropriate advertisement", "Inappropriate", 2, 1);
INSERT INTO Inappropriate VALUES(4, '2017-12-20',"Inappropriate advertisement", "Inappropriate", 3, 3);

INSERT INTO rent_real_estate VALUES(1, '2017-02-02', '2017-03-02', 2);
INSERT INTO rent_real_estate VALUES(2, '2017-04-09', '2017-04-30', 2);
INSERT INTO rent_real_estate VALUES(3, '2017-05-01', '2017-05-31', 2);
INSERT INTO rent_real_estate VALUES(4, '2017-06-05', '2017-06-22', 2);

INSERT INTO Comment VALUES (1, '2016-04-24', 'Opis komentara', 1, 1);
INSERT INTO Comment VALUES (2, '2016-01-23', 'Comm desc', 2, 2);
INSERT INTO Comment VALUES (3, '2016-04-24', 'Opis komentara', 1, 2);
INSERT INTO Comment VALUES (4, '2016-01-23', 'Comm desc', 2, 2);
INSERT INTO Comment VALUES (5, '2016-04-24', 'Opis komentara', 1, 3);
INSERT INTO Comment VALUES (6, '2016-01-23', 'Comm desc', 2, 4);