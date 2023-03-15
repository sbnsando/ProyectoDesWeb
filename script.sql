drop table if exists tools;
drop table if exists users;
drop table if exists brands;
drop table if exists cities;


CREATE TABLE brands(
    id int primary key not null AUTO_INCREMENT,
    name varchar(255) not null
);

INSERT INTO brands values (1, 'Martillator');
INSERT INTO brands values (2, 'Palillin');
INSERT INTO brands values (3, 'CAT');

CREATE TABLE tools(
    id int primary key not null AUTO_INCREMENT,
    id_brand int not null,
    name varchar(255) not null,
    description varchar(255),
    price int not null,
    img varchar(255) not null,
    country varchar(255) not null,
    cities varchar(255) not null,
    quantity int not null,
    FOREIGN KEY (id) REFERENCES brands(id)
);

INSERT INTO tools values (1, 1, 'Martillo', 'Muy bueno', 40000, 'www.marti.com', 'Dinamarca', '1, 2, 3', 30);

INSERT INTO tools values (2, 2, 'taladro', 'Muy bueno', 100000, 'www.taladro.com', 'Brasil', '1, 2', 50);

INSERT INTO tools values (3, 3, 'destornillador', 'Muy bueno', 100000, 'www.desto.com', 'Brasil', '1, 2', 50);

CREATE TABLE cities(
    id int primary key not null AUTO_INCREMENT,
    name varchar(255) not null
);

INSERT INTO cities values (1, 'Bogota');
INSERT INTO cities values (2, 'Medellin');
INSERT INTO cities values (3, 'Ibague');

CREATE TABLE users(
    id int primary key not null AUTO_INCREMENT,
    identity_numb int not null,
    name varchar(255) not null,
    birthday date not null,
    id_city int not null,
    admin boolean not null,
    active boolean not null,
    FOREIGN KEY (id) REFERENCES cities(id)
);

INSERT INTO users values (1, 1052415, 'Esteban Pedraza', '1999-05-23', 1, TRUE, TRUE);

INSERT INTO users values (2, 1010248, 'Sebastian Sandoval', '1999-12-26', 1, FALSE, FALSE);