set foreign_key_checks = 0;

truncate Admin;
truncate Authority;
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

INSERT INTO User VALUES (1, 'Balzakova', 'Novi Sad', 'user@gmail.com', 'image', 'User', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+4129494', 'Useran', 'user', '52389028', false, false, 2, 5, 2, null);
INSERT INTO User VALUES (2, 'Niska', 'Nis', 'user2@gmail.com', 'image', 'User2', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+523525', 'Useran2', 'user2', '646546', false, false, 3, 2, 2, null);
INSERT INTO User VALUES (3, 'Kragujevacka', 'Kragujevac', 'user3@gmail.com', 'image', 'User3', '$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq', '+3646363', 'Useran3', 'user3', '32525', false, false, 2, 2, 2, null);

INSERT INTO Location VALUES (1, 'Balzakova 6', 'Novi Sad', 'Liman', 21000);
INSERT INTO Location VALUES (2, 'Bul. Mihaila Pupina', 'Beograd', 'Kosutnjak', 11000);
INSERT INTO Location VALUES (3, 'Bulevar Oslobodjenja', 'Novi Sad', 'Bulevar', 21000);
INSERT INTO Location VALUES (4, 'Miselinova', 'Nis', 'Deo Nisa', 23560);
INSERT INTO Location VALUES (5, 'Mose Pijade', 'Sremska Kamenica', 'Kod korduna', 21208);

INSERT INTO Admin VALUES (1, 'Balzakova', 'Novi Sad', 'admin@gmail.com', 'image', 'Admin', 'a', '+381412941', 'Adminovski', 'admin', 1);

INSERT INTO Verifier VALUES (1, 'Balzakova', 'Novi Sad', 'verifier@gmail.com', 'i.jpg', 'Verifier', 'v', '+381594219', 'Vierifieric', 'verifier', 3);
INSERT INTO Verifier VALUES (2, 'Beogradska', 'Beograd', 'verifier2@gmail.com', 'img.jpg', 'Verifier2', 'v', '+381242459', 'Vierifieric2', 'verifier2', 3);
INSERT INTO Verifier VALUES (3, 'Mose Pijade', 'Kamenica', 'verifier3@gmail.com', 'img.png', 'Verifier3', 'v', '+38121461825', 'Verif3', 'verifier3', 3);

INSERT INTO Company VALUES (1, 'Kompanija Neka', '+3819214814', 'www.kompanijaneka.com', 1);
INSERT INTO Company VALUES (2, 'Nasa Kompanija', '+38142342', 'www.nasakompanija.com', 2);
INSERT INTO Company VALUES (3, 'Company', '+38164564', 'www.comany.com', 3);
INSERT INTO Company VALUES (4, 'Kompanija DOO', '+3816456456', 'www.kompanijadoo.com', 4);
INSERT INTO Company VALUES (5, 'Co doo', '+9940424444', 'www.coodoo.gb', 1);

INSERT INTO User VALUES (4, 'Balzakova', 'Novi Sad', 'clerk@gmail.com', 'image', 'Clerk', 'c', '+12331203', 'Clerkan', 'clerk', '21345411', false, true, 1, 2, 2, 1);
INSERT INTO User VALUES (5, 'Novosadska', 'Novi Sad', 'clerk2@gmail.com', 'image', 'Clerk2', 'c', '+15232', 'Clerkan2', 'clerk2', '9678678', true, true, 2, 2, 2, 2);

INSERT INTO Real_Estate VALUES (1, 1890, 0, 'radijatori', 'image', 456, 'Kuca velika', 2, 3, 3, 324034, 'TV,Klima', 'Kuca', 1);
INSERT INTO Real_Estate VALUES (2, 1950, 1, 'radijatori', 'image', 100, 'Stan na brdu', 3, 5, 4, 31242, 'TV,Klima,Namestak', 'Stan', 2);
INSERT INTO Real_Estate VALUES (3, 1960, 2, 'radijatori', 'image', 52, 'Kuca sa bazenom', 2, 2, 2, 24034, 'TV,Klima,Bazen', 'Kuca', 3);
INSERT INTO Real_Estate VALUES (4, 1994, 1, 'radijatori', 'image', 3506, 'Kancelarija u centru', 2, 3, 2, 5434, 'Nista', 'Kancelarija', 4);

INSERT INTO Advertisment VALUES (1, '2016-12-20', '2016-12-23', 10, '+481924842', '2017-12-20', 'buy', '4', null, null, 2, 1);
INSERT INTO Advertisment VALUES (2, '2016-12-20', '2016-12-23', 11,'+3453535', '2017-12-20', 'buy', '5', null, null, 1, 2);
INSERT INTO Advertisment VALUES (3, '2016-12-20', '2016-12-23', 47,'+52432342', '2017-12-20', 'rent', '3.2', null, null, 4, 2);

INSERT INTO Inappropriate VALUES (1, 'Inappropriate advertisement', 'Already exists', 1);
INSERT INTO Inappropriate VALUES (2, 'Forbidden pictures', 'Forbidden', 1);

INSERT INTO Comment VALUES (1, '2016-04-24', 'Opis komentara', 'Komentar1', 1, 1);
INSERT INTO Comment VALUES (2, '2016-01-23', 'Comm desc', 'Comment2', 2, 2);