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

INSERT INTO MENU (DATE, NAME, RESTAURANT_ID)
VALUES ('2023-06-23','cheap', 1),
       ('2023-06-23','expensive', 2);

INSERT INTO DISH (NAME, PRICE)
VALUES ( 'Soup', '50'),
       ('Coffee', '50'),
       ('Bread', '5'),
       ( 'Soup', '100'),
       ('Tea', '100'),
       ('Bread', '10');

INSERT INTO VOTE (RESTAURANT_ID, USER_ID)
VALUES (1, 1),
       (1, 2),
       (2, 3);




