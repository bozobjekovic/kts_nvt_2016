select * from location;
select * from user;
select * from advertisment;
select * from admin;
select * from verifier;

drop table Admin;
drop table Clerk;
drop table Comment;
drop table Company;
drop table Inappropriate;
drop table Advertisment;
drop table User;
drop table Real_estate;
drop table Verifier;
drop table Location;

set foreign_key_checks = 0;

truncate Admin;
truncate Clerk;
truncate Comment;
truncate Inappropriate;
truncate Company;
truncate Advertisment;
truncate User;
truncate Real_estate;
truncate Verifier;
truncate Location;

set foreign_key_checks = 1;

insert into authority (name) values ('ADMIN');
insert into authority (name) values ('USER');
insert into authority (name) values ('VERIFIER');

INSERT INTO User VALUES (1, 'Balzakova', 'Novi Sad', 'user@gmail.com', 'image', 'User', 'u', '+4129494', 'Useran', 'user', '52389028', false, 5, 2, null);
INSERT INTO User VALUES (2, 'Niska', 'Nis', 'user2@gmail.com', 'image', 'User2', 'u', '+523525', 'Useran2', 'user2', '646546', false, 3, 2, null);
INSERT INTO User VALUES (3, 'Kragujevacka', 'Kragujevac', 'user3@gmail.com', 'image', 'User3', 'u', '+3646363', 'Useran3', 'user3', '32525', false, 2, 2, null);

INSERT INTO Location VALUES (1, 'Balzakova 6', 'Novi Sad', 'Liman', 21000);
INSERT INTO Location VALUES (2, 'Bul. Mihaila Pupina', 'Beograd', 'Kosutnjak', 11000);
INSERT INTO Location VALUES (3, 'Bulevar Oslobodjenja', 'Novi Sad', 'Bulevar', 21000);
INSERT INTO Location VALUES (4, 'Miselinova', 'Nis', 'Deo Nisa', 23560);

INSERT INTO Admin VALUES (1, 'Balzakova', 'Novi Sad', 'admin@gmail.com', 'image', 'Admin', 'a', '+381412941', 'Adminovski', 'admin', 1);

INSERT INTO Verifier VALUES (1, 'Balzakova', 'Novi Sad', 'verifier@gmail.com', '', 'Verifier', 'v', '+381594219', 'Vierifieric', 'verifier', 3);
INSERT INTO Verifier VALUES (2, 'Beogradska', 'Beograd', 'verifier2@gmail.com', '', 'Verifier2', 'v', '+381242459', 'Vierifieric2', 'verifier2', 3);

INSERT INTO Company VALUES (1, 'Kompanija Neka', '+3819214814', 'www.kompanijaneka.com', 1);
INSERT INTO Company VALUES (2, 'Nasa Kompanija', '+38142342', 'www.nasakompanija.com', 2);
INSERT INTO Company VALUES (3, 'Company', '+38164564', 'www.comany.com', 3);
INSERT INTO Company VALUES (4, 'Kompanija DOO', '+3816456456', 'www.kompanijadoo.com', 4);

INSERT INTO User VALUES (4, 'Balzakova', 'Novi Sad', 'clerk@gmail.com', 'image', 'Clerk', 'c', '+12331203', 'Clerkan', 'clerk', '21345411', true, 1, 2, 1);
INSERT INTO User VALUES (5, 'Novosadska', 'Novi Sad', 'clerk2@gmail.com', 'image', 'Clerk2', 'c', '+15232', 'Clerkan2', 'clerk2', '9678678', true, 2, 2, 2);

INSERT INTO Real_Estate VALUES (1, 0, 'radijatori', 'image', '456m2', 'Kuca velika', 324034, 'TV,Klima', 'Kuca', 1);
INSERT INTO Real_Estate VALUES (2, 1, 'radijatori', 'image', '100m2', 'Stan na brdu', 31242, 'TV,Klima,Namestak', 'Stan', 2);
INSERT INTO Real_Estate VALUES (3, 2, 'radijatori', 'image', '52m2', 'Kuca sa bazenom', 24034, 'TV,Klima,Bazen', 'Kuca', 3);
INSERT INTO Real_Estate VALUES (4, 1, 'radijatori', 'image', '3506m2', 'Kancelarija u centru', 5434, 'Nista', 'Kancelarija', 4);

INSERT INTO Advertisment VALUES (1, '2016-12-20', '2016-12-23', '+481924842', '2017-12-20', 'sell', '4', null, null, 2, 1);
INSERT INTO Advertisment VALUES (2, '2016-12-20', '2016-12-23', '+3453535', '2017-12-20', 'sell', '5', null, null, 1, 2);
INSERT INTO Advertisment VALUES (3, '2016-12-20', '2016-12-23', '+52432342', '2017-12-20', 'rent', '3.2', null, null, 4, 2);


