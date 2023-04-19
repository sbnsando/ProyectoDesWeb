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
INSERT INTO brands values (3, 'DSTOR');
INSERT INTO brands values (4, 'CAT');
INSERT INTO brands values (5, 'RED');
INSERT INTO brands values (6, 'VLACK');
INSERT INTO brands values (7, 'OITNB');
INSERT INTO brands values (8, 'BRWN');

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

INSERT INTO tools values (1, 1, 'Martillo', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo,', 40000, 'https://upload.wikimedia.org/wikipedia/commons/c/ce/Martillo.png', 'Dinamarca', '1, 2, 3', 30);

INSERT INTO tools values (2, 2, 'taladro', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo,', 100000, 'https://assets.boschenlinea.com/600x600/o294553v54_lv-171149-49-gsb450_re_dyn.png', 'Brasil', '1, 2', 50);

INSERT INTO tools values (3, 3, 'destornillador', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo,', 100000, 'https://inversionesproin.com/wp-content/uploads/2020/07/60004.jpeg', 'Brasil', '1, 2', 50);

INSERT INTO tools values (4, 4, 'Yellow tool', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel.', 14232, 'https://img.freepik.com/premium-vector/mechanic-tool-set-vector-construction-tools-home-repairs-isolated-white-background_68708-1541.jpg?w=600&q=60', 'Brasil', '1, 2', 50);

INSERT INTO tools values (5, 5, 'Red tool', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel.', 56577, 'https://images.unsplash.com/photo-1530124566582-a618bc2615dc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=600&q=60', 'Colombia', '1, 2', 34);

INSERT INTO tools values (6, 6, 'Black tool', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel.', 43777, 'https://images.unsplash.com/photo-1502068898470-ad70c83938be?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=600&q=60', 'Colombia', '1, 2', 543);

INSERT INTO tools values (7, 7, 'Orange tool', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel.', 435235, 'https://images.unsplash.com/photo-1567361808960-dec9cb578182?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=600&q=60', 'Colombia', '1, 2', 65);

INSERT INTO tools values (8, 8, 'Brown tool', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel.', 67575, 'https://images.unsplash.com/photo-1586864387789-628af9feed72?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDN8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60', 'Colombia', '1, 2', 33);

INSERT INTO tools values (9, 4, 'Yellow tool', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel.', 33456, 'https://img.freepik.com/premium-vector/mechanic-tool-set-vector-construction-tools-home-repairs-isolated-white-background_68708-1541.jpg?w=600&q=60', 'Brasil', '1, 2', 331);

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