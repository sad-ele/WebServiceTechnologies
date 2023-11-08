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
VALUES ('Запомню', 'Улица Восток', 'alternative', '3:05', 2021);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('KoKoKo', 'Shortparis', 'rock', '3:50', 2021);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Promises', 'Adema', 'rock', '4:16', 2003);
INSERT INTO songs(name, band, genre, time, year)
VALUES ('Таллинн', 'Zero People', 'indie-rock', '4:23', 2011);