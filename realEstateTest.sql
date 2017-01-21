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

INSERT INTO Location VALUES (1, 'Novi Sad', 'Liman', 21000);
INSERT INTO Location VALUES (2, 'Beograd', 'Kosutnjak', 11000);
INSERT INTO Location VALUES (3, 'Novi Sad', 'Bulevar', 21000);
INSERT INTO Location VALUES (4, 'Nis', 'Deo Nisa', 23560);
INSERT INTO Location VALUES (5, 'NS', 'Lim', 21000);

INSERT INTO Company VALUES (1, 'Balzakova 6', 'Kompanija Neka', '+3819214814', 'www.kompanijaneka.com', true, 1);
INSERT INTO Company VALUES (2, 'Albanska', 'Nasa Kompanija', '+38142342', 'www.nasakompanija.com', true, 5);
INSERT INTO Company VALUES (3, 'Bulevar Oslobodjenja', 'Company', '+38164564', 'www.comany.com', false, 3);
INSERT INTO Company VALUES (4, 'Anete Andrejević', 'Kompanija DOO', '+3816456456', 'www.kompanijadoo.com', false, 4);

INSERT INTO User VALUES (1, 'Balzakova', 'Novi Sad', 'user@gmail.com', 'image', 'User', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+4129494', 'Useran', 'user', '52389028', false, false, 2, 5, 2, null, 1);
INSERT INTO User VALUES (2, 'Niska', 'Nis', 'user2@gmail.com', 'image', 'User2', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+523525', 'Useran2', 'user2', '646546', false, false, 3, 2, 2, null, null);
INSERT INTO User VALUES (3, 'Kragujevacka', 'Kragujevac', 'user3@gmail.com', 'image', 'User3', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+3646363', 'Useran3', 'user3', '32525', false, false, 2, 2, 2, null, 3);

INSERT INTO Admin VALUES (1, 'Balzakova', 'Novi Sad', 'admin@gmail.com', 'image', 'Admin', 'a', '+381412941', 'Adminovski', 'admin', 1);

INSERT INTO Verifier VALUES (1, 'verifier@gmail.com', '$2a$04$TjNk.I2AJ/QGBZKyOO6uruAx9MRoHsYKTJHcTd35rmmULDoM/XiXy','Verifier', 3);
INSERT INTO Verifier VALUES (2, 'verifier2@gmail.com','$2a$04$TjNk.I2AJ/QGBZKyOO6uruAx9MRoHsYKTJHcTd35rmmULDoM/XiXy', 'Verifier2', 3);

INSERT INTO User VALUES (4, 'Balzakova', 'Novi Sad', 'clerk@gmail.com', 'image', 'Clerk', '$2a$06$ESTR0aRvH5s4McmiWeiXl.DqRxveC/lruQKFD7UVkvBRwOEdp2Qty', '+12331203', 'Clerkan', 'clerk', '21345411', false, true, 1, 2, 2, null, 1);
INSERT INTO User VALUES (5, 'Novosadska', 'Novi Sad', 'clerk2@gmail.com', 'image', 'Clerk2', '$2a$06$ESTR0aRvH5s4McmiWeiXl.DqRxveC/lruQKFD7UVkvBRwOEdp2Qty', '+15232', 'Clerkan2', 'clerk2', '9678678', true, true, 2, 2, 2, null, 2);

INSERT INTO Real_Estate VALUES (1, 'Balzakova 6', 1890, 0, 'radijatori', 456, 2, 3, 3, 0, 'TV,Klima', 'Kuca', 1);
INSERT INTO Real_Estate VALUES (2, 'Bul. Mihaila Pupina', 1950, 1, 'radijatori', 100, 3, 5, 4, 0, 'TV,Klima,Namestak', 'Stan', 2);
INSERT INTO Real_Estate VALUES (3, 'Bul. Mihaila Pupina', 1960, 2, 'radijatori', 52,  2, 2, 2, 1, 'TV,Klima,Bazen', 'Kuca', 3);
INSERT INTO Real_Estate VALUES (4, 'Bulevar Oslobodjenja', 1994, 1, 'radijatori', 3506, 2, 3, 2, 0, 'Nista', 'Kancelarija', 4);
INSERT INTO Real_Estate VALUES (5, 'Miselinova', 1994, 1, 'radijatori', 3506, 2, 3, 2, 0, 'Nista', 'Kancelarija', 4);

INSERT INTO Advertisment VALUES (1, '2016-12-20', 'img', 0, '2016-12-23', 'Kuca velika', 10, '+481924842', 234412, '2017-12-20', 'sell', '4', null, 2, 2, 1);
INSERT INTO Advertisment VALUES (2, '2016-12-20', 'img', 0, '2016-12-23', 'Stan na brdu', 11,'+3453535', 43242,'2017-12-20', 'sell', '5', null, 1, 1, 2);
INSERT INTO Advertisment VALUES (3, '2016-12-20', 'img', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+52432342', 423424, '2017-12-20', 'rent', '3.2', null, 3, 4, 2);
INSERT INTO Advertisment VALUES (4, '2016-12-20', 'img', 0,'2016-12-23',  'Kuca sa bazenom', 47,'+5243234982', 423424, '2017-12-20', 'rent', '3.2', null, 1, 5, null);

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