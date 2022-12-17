CREATE TABLE carts
(
    id       bigint NOT NULL AUTO_INCREMENT,
    user_id  bigint NOT NULL,
    book_id  bigint NOT NULL,
    quantity int    NOT NULL DEFAULT 1,
    status   int    NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

ALTER TABLE users
    ADD role varchar(30) DEFAULT 'CLIENT';

ALTER TABLE books
    ADD price int;