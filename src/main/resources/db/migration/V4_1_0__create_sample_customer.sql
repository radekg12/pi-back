INSERT INTO testdb.user_role (id, role_name)
VALUES (1, 'customer');

INSERT INTO testdb.user_account (id, username, password, user_role_id)
VALUES (1, 'jan.kowalski@mail.com', 'Password1!', 1);

INSERT INTO testdb.address (id, street, city, postcode)
VALUES (1, 'Firmowa 12', 'Warszawa', 100);

INSERT INTO testdb.customer (id, company_name, first_name, last_name, phone_number, email, address_id, user_account_id)
VALUES (1, 'Firma X', 'Jan', 'Kowalski', 123456789, 'jan.kowalski@mail.com', 1, 1);
