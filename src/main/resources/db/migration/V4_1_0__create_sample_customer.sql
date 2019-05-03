INSERT INTO testdb.authority (name)
VALUES ('ROLE_USER');

INSERT INTO testdb.authority (name)
VALUES ('ROLE_ADMIN');

INSERT INTO testdb.address (id, street, city, postcode)
VALUES (1, 'Firmowa 12', 'Warszawa', 10002);

INSERT INTO testdb.address (id, street, city, postcode)
VALUES (2, 'Kołowa 20', 'Warszawa', 32564);

INSERT INTO testdb.address (id, street, city, postcode)
VALUES (3, 'Warszawska 13', 'Warszawa', 25485);

INSERT INTO testdb.customer (id, first_name, last_name, phone_number, email, address_id, password_hash,
                             activated)
VALUES (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@mail.com', 1,
        '$2a$10$aa2HndD0VRjGpxWHl28PtO8FzeUndEfIKGQgLWcih.xugXpVjY4Fy', true);

INSERT INTO testdb.customer (id, first_name, last_name, phone_number, email, address_id, password_hash,
                             activated)
VALUES (2, 'Marek', 'Nowak', '987654321', 'marek.nowak@mail.com', 2,
        '$2a$10$aa2HndD0VRjGpxWHl28PtO8FzeUndEfIKGQgLWcih.xugXpVjY4Fy', true);

INSERT INTO testdb.customer (id, first_name, last_name, email, password_hash, activated)
VALUES (3, 'Amin', 'Adminiusz', 'admin@mail.com',
        '$2a$10$aa2HndD0VRjGpxWHl28PtO8FzeUndEfIKGQgLWcih.xugXpVjY4Fy', true);

INSERT INTO testdb.customer_authority (customer_id, authority_name)
VALUES (1, 'ROLE_USER');
INSERT INTO testdb.customer_authority (customer_id, authority_name)
VALUES (2, 'ROLE_USER');
INSERT INTO testdb.customer_authority (customer_id, authority_name)
VALUES (3, 'ROLE_ADMIN');
