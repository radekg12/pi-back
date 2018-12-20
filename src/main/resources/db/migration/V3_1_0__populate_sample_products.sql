INSERT INTO testdb.product (id, name, description, company, quantity_in_stock, unit_price, subcategory_id, image_url)
VALUES (1, 'Microsoft Surface Pro m3-7Y30/4GB/128SSD/Win10P',
        'Surface Laptop to smukły, lekki i elegancki laptop, zapewniający wysoką wydajność pracy na klawiaturze obleczonej tkaniną Alcantara, ekranie dotykowym i baterii, która starcza na cały dzień.


Nowy Surface Pro stanowi połączenie najlepszego w swojej klasie laptopa z wszechstronnym komputerem typu all-in-one i tabletem.Oszałamiający ekran PixelSense obsługuje pióro Surface i funkcje dotykowe, a 13, 5 godziny pracy baterii wystarczy, by pracować cały dzień i grać całą noc.',
        'Microsoft', 58, 359900, 4,
        'https://stat-m2.ms-online.pl/media/cache/gallery/rc/cfcrh0yi/images/20/20716176/microsoft-surface-laptop-platinum-2.jpg');


INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Wymiary ', ' 12,13" x 8,79" x 57" (308,1 mm x 223,27 mm x 14,48 mm)
 ', 1);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Wyświetlacz', 'Ekran: PixelSense™ o przekątnej 13.5"
Rozdzielczość: 2256 x 1504 (201 ppi)
Współczynnik proporcji: 3:2
3,4 mln pikseli
Obsługa Pióra Surface
Dotyk: 10-punktowy wielodotyk
Corning® Gorilla® Glass 3', 1);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Pamięć', '4 GB, 8 GB lub 16 GB RAM', 1);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Procesor', 'Intel® Core™ i5 lub i7 7. generacji', 1);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Masa', 'i5: 2,76 funta (1,252 g)
i7: 2,83 funta (1,283 g)', 1);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Czas pracy baterii', 'Do 14.5 godzin odtwarzania wideo', 1);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Co jest w opakowaniu', 'Surface Laptop
Zasilacz
Przewodnik „Szybki start”
Dokumenty gwarancyjne i dotyczące bezpieczeństwa ', 1);


# ----------------------------------------------------------------------------------------------------
# ----------------------------------------------------------------------------------------------------

INSERT INTO testdb.product (id, name, description, company, quantity_in_stock, unit_price, subcategory_id, image_url)
VALUES (2, 'MacBook Pro 15 2018 i9 32GB 4TB 560X 4GB Space',
        'Laptop z ekranem o przekątnej 13.3 cali oraz rozdzielczości 2560 x 1600 pikseli, wyposażony w procesor Intel Core i5-7360U o częstotliwości 2.3 GHz, pamięć RAM DDR4 o wielkości 8 GB. Dysk twardy SSD o pojemności 128GB. Karta graficzna to Intel Iris Plus 640. Zainstalowany system operacyjny to macOS Sierra.


Macbook Pro niezmiennie urzeka swoją ponadczasową stylistyką. Mocne komponenty jak na tak kompaktową obudowę pozwolą Wam wejść w ekosystem firmy Apple ze stylem, a jeżeli jest to kolejny Macbook w Waszej kolekcji z pewnością nie zawiedziecie się na nim.',
        'Apple', 58, 579885, 4,
        'https://media.sweetwater.com/api/i/f-webp__q-82__ha-a239a191f23f359f__hmac-49654ca0214849603de76a092e533c8e99cae588/images/items/750/MBP15T22G256-large.jpg');


INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Wymiary ', ' 12,13" x 8,79" x 57" (308,1 mm x 223,27 mm x 14,48 mm)
 ', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Wyświetlacz', 'Przekątna: 13.3 cali
Rozdzielczość: 2560 x 1600 pikseli
Typ matrycy: Błyszcząca', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Pamięć', '8 GB pamięci LPDDR3 1866 MHz', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Procesor', 'Dwurdzeniowy procesor Intel Core m3 1,2 GHz 7. generacji,
Turbo Boost do 3,0 GHz', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Oprogramowanie', 'MacOS Sierra', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Masa', '1.37 kg', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Komunikacja', 'Wi-Fi - standard: 802.11 a/b/g/n/ac
Bluetooth: 4.2
Pozostałe złącza: 2 x Thunderbolt', 2);

INSERT INTO testdb.specification_position (name, value, product_id)
VALUES ('Zawartość opakowania', 'MacBook
Przewód USB-C do ładowania (2 m)
Zasilacz USB-C o mocy 30 W', 2);