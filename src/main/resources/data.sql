INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('User3', 'user3@yandex.ru', '{noop}password'),
       ('User4', 'user4@yandex.ru', '{noop}password'),
       ('User5', 'user5@yandex.ru', '{noop}password'),
       ('Admin', 'admin@yandex.ru', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('ADMIN', 6),
       ('USER', 6);

INSERT INTO RESTAURANT (NAME)
VALUES ('4 stars'),
       ('5 stars');

INSERT INTO MENU (NAME, RESTAURANT_ID)
VALUES ('cheap', 1),
       ('expensive', 2);

INSERT INTO MEAL (NAME, PRICE, MENU_ID)
VALUES ( 'Soup', '50', 1),
       ('coffee', '50', 1),
       ('bread', '5', 1),
       ( 'Soup', '100', 2),
       ('tea', '100', 2),
       ('bread', '5', 2);

INSERT INTO VOICE (RESTAURANT_ID, USER_ID)
VALUES (1, 1),
       (1, 2),
       (2, 3);




