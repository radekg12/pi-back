INSERT INTO testdb.order_status_category (id, name)
VALUES (1, 'nowe');
INSERT INTO testdb.order_status_category (id, name)
VALUES (2, 'w realizacji');
INSERT INTO testdb.order_status_category (id, name)
VALUES (3, 'zamknięte (zrealizowane)');
INSERT INTO testdb.order_status_category (id, name)
VALUES (4, 'zamknięte (niezrealizowane)');

INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (1, 1, 'złożone');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (2, 2, 'przyjęte do realizacji');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (3, 2, 'oczekiwanie na dostawę');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (4, 2, 'w trakcie kompletowania');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (5, 2, 'oczekiwanie na płatność');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (6, 2, 'gotowe do wysłania');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (7, 3, 'przesyłka wysłana');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (8, 4, 'anulowane');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (9, 4, 'odrzucone');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (10, 4, 'zwrócone');
INSERT INTO testdb.order_status (id, order_status_category_id, name)
VALUES (11, 4, 'reklamowane');

INSERT INTO testdb.delivery_type (id, name, price)
VALUES (1, 'odbiór osobisty', 0);
INSERT INTO testdb.delivery_type (id, name, price)
VALUES (2, 'paczkomaty InPost', 990);
INSERT INTO testdb.delivery_type (id, name, price)
VALUES (3, 'dostawa kurierem', 2000);

INSERT INTO testdb.payment_method (id, name, price)
VALUES (1, 'gotówka przy odbiorze', 500);
INSERT INTO testdb.payment_method (id, name, price)
VALUES (2, 'przelew bankowy', 0);
INSERT INTO testdb.payment_method (id, name, price)
VALUES (3, 'płatność kartą w paczkomacie', 0);
INSERT INTO testdb.payment_method (id, name, price)
VALUES (4, 'e-płatność PayU', 0);
INSERT INTO testdb.payment_method (id, name, price)
VALUES (5, 'PayPal', 0);

INSERT INTO testdb.product_category (id, category_name)
VALUES (1, 'komputery stacjonarne');
INSERT INTO testdb.product_category (id, category_name)
VALUES (2, 'Laptopy i tablety');
INSERT INTO testdb.product_category (id, category_name)
VALUES (3, 'akcesoria');

INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (1, 1, 'Komputery/Serwery');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (2, 1, 'Akcesoria komputerowe');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (3, 1, 'Akcesoria do monitorów');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (4, 2, 'Laptopy/Notebooki/Ultrabooki');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (5, 2, 'Laptopy 2 w 1');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (6, 2, 'Tablety');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (7, 2, 'Czytniki ebook');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (8, 3, 'Głośniki');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (9, 3, 'Pamięć flash');
INSERT INTO testdb.product_subcategory (id, category_id, subcategory_name)
VALUES (10, 3, 'Kable i przejściówki');