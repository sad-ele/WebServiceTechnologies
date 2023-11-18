CREATE TABLE songs (
                       id serial PRIMARY KEY,
                       name varchar(255),
                       band varchar(255),
                       genre varchar(255),
                       time varchar(255),
                       year integer
);

INSERT INTO songs(name, band, genre, time, year)
VALUES ('Samurai', 'Call Me Karizma', 'alternative', '2:12', 2023);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Freaks', 'Television Blonde', 'alternative', '2:33', 2020);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Special needs', 'Placebo', 'alternative', '5:16', 2003);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Across the ocean', 'Our Last Night', 'rock', '4:22', 2010);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Promises', 'Adema', 'rock', '4:16', 2003);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Only One', 'Storm the sky', 'metal', '4:06', 2015);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Search Party', 'Enter Shikari', 'rock', '4:06', 2012);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Conspiracy', 'Our Last Night', 'rock', '3:38', 2012);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Same Old War', 'Our Last Night', 'rock', '4:05', 2013);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Ivory Tower', 'Our Last Night', 'alternative', '3:16', 2017);