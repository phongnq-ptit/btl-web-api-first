CREATE TABLE users
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    email    varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    name     varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id    bigint       NOT NULL AUTO_INCREMENT,
    label varchar(255) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE images
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    url      varchar(255) NOT NULL UNIQUE,
    public_id varchar(255) NOT NULL,
    book_id   bigint       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE books
(
    id          bigint       NOT NULL AUTO_INCREMENT,
    title       varchar(255) NOT NULL UNIQUE,
    author      varchar(255) NOT NULL,
    date        varchar(255) NOT NULL,
    page        varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    category_id  bigint       NOT NULL,
    PRIMARY KEY (id)
);