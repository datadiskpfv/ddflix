-- USER, ROLE and ADDRESS TEST DATA
INSERT INTO country(id, iso, tld, name, date_created, last_updated) VALUES
	('GI', 'GI', '.gi', 'Gibraltar', now(), now()),
	('GK', 'GK', '.gk', 'Guernsey', now(), now()),
	('UK', 'GB', '.uk', 'United Kingdom', now(), now());

INSERT INTO city(id, city, country_id, date_created, last_updated) VALUES
  (1, 'Milton Keynes', 'UK', now(), now()),
  (2, 'Great Yarmouth', 'UK', now(), now()),
  (3, 'Lincoln', 'UK', now(), now()),
  (4, 'London', 'UK', now(), now()),
  (5, 'Liverpool', 'UK', now(), now()),
  (6, 'Aylesbury', 'UK', now(), now()),
  (7, 'Aston Clinton', 'UK', now(), now()),
  (8, 'Gibraltar', 'GK', now(), now()),
  (9, 'Saint Peter Port', 'GI', now(), now());

INSERT INTO  address(id, line1, line2, line3, line4, city_id, postcode, date_created, last_updated) VALUES
  (1, 'The Willows', '1 Tree Lane', 'Tree Estate', '', 1, 'TR123EE', now(), now()),
  (2, 'The Beach', '1 Sandy Lane', 'Watery Estate', '', 2, 'BE123CH', now(), now()),
  (3, 'The River', '1 Canal Lane', 'Lake Estate', '', 3, 'RI123VR', now(), now()),
  (4, 'Dummy 1', '1 Dummy Road', 'Dummy Estate', '', 4, 'DU121MY', now(), now()),
  (5, 'Dummy 2', '2 Dummy Road', 'Dummy Estate', '', 5, 'DU122MY', now(), now()),
  (6, 'Dummy 3', '3 Dummy Road', 'Dummy Estate', '', 6, 'DU123MY', now(), now()),
  (7, 'Dummy 4', '4 Dummy Road', 'Dummy Estate', '', 7, 'DU124MY', now(), now()),
  (8, 'Dummy 5', '5 Dummy Road', 'Dummy Estate', '', 8, 'DU125MY', now(), now()),
  (9, 'Dummy 6', '6 Dummy Road', 'Dummy Estate', '', 9, 'DU126MY', now(), now());

INSERT INTO user(id, email, password, username, default_billing_address, default_shipping_address, date_created, last_updated) VALUES
  (1, "will.hay@example.com", "password", "Will_1", 1, 1, sysdate(), sysdate()),
  (2, "graham.moffatt@example.com", "password", "Graham_1", 2, 2, sysdate(), sysdate()),
  (3, "moore.marriott@example.com", "password", "Moore_1", 3, 3, sysdate(), sysdate());

INSERT INTO user_shipping_address(user_id, address_id) VALUES
  (1,1),
  (2,2),
  (3,3),
  (1,4),
  (1,5),
  (1,6);

INSERT INTO role(id, name, date_created, last_updated) VALUES
  (1, 'ADMIN', sysdate(), sysdate()),
  (2, 'USER', sysdate(), sysdate()),
  (3, 'UPLOADER', sysdate(), sysdate());

INSERT INTO user_role(user_id, role_id) VALUES
  (1,1),
  (1,3),
  (2,2),
  (3,3);

-- FILM, CLASSIFICATION and GENRE TEST DATA
INSERT INTO classification(id, rating, image_name, date_created, last_updated) VALUES
  (1, 'U', 'U.png', sysdate(), sysdate()),
  (2, 'PG', 'PG.png', sysdate(), sysdate()),
  (3, '12', '12.png', sysdate(), sysdate()),
  (4, '15', '15.png', sysdate(), sysdate()),
  (5, '18', '18.png', sysdate(), sysdate()),
  (6, '99', '99.png', sysdate(), sysdate());

INSERT INTO genre(id, name, date_created, last_updated) VALUES
  (1, 'Horror', sysdate(), sysdate()),
  (2, 'Action', sysdate(), sysdate()),
  (3, 'Children', sysdate(), sysdate()),
  (4, 'Romance', sysdate(), sysdate()),
  (5, 'Thriller', sysdate(), sysdate()),
  (6, 'Sci-Fi', sysdate(), sysdate()),
  (7, 'Western', sysdate(), sysdate()),
  (8, 'Junk', sysdate(), sysdate());

INSERT INTO film(id, title, description, release_date, status, cover_image, classification_id, date_created, last_updated) VALUES
  (1, 'Alien', 'Sci-Fi horror movie', STR_TO_DATE('01-10-1981', '%d-%m-%Y'), true, 'alien.jpg', 5, sysdate(), sysdate()),
  (2, 'Ant', 'Action Marvel movie', STR_TO_DATE('12-09-2015', '%d-%m-%Y'), true, 'ant.jpg', 3, sysdate(), sysdate()),
  (3, 'Black Mass', 'Action Gangster movie', STR_TO_DATE('20-02-2015', '%d-%m-%Y'), true, 'black_mass.jpg', 5, sysdate(), sysdate()),
  (4, 'Bone Tomahawk', 'Action Western movie', STR_TO_DATE('01-01-2015', '%d-%m-%Y'), true, 'bone_tomahawk.jpg', 4, sysdate(), sysdate()),
  (5, 'Star Wars', 'Sci-Fi movie', STR_TO_DATE('30-05-1977', '%d-%m-%Y'), true, 'star_wars.jpg', 2, sysdate(), sysdate()),
  (6, 'Predator', 'Action Alien movie', STR_TO_DATE('09-06-1988', '%d-%m-%Y'), true, 'predator.jpg', 5, sysdate(), sysdate()),
  (7, 'Rambo', 'Action Army movie', STR_TO_DATE('04-06-1987', '%d-%m-%Y'), true, 'rambo.jpg', 5, sysdate(), sysdate()),
  (8, 'Harry Potter', 'Magical movie', STR_TO_DATE('06-03-2001', '%d-%m-%Y'), true, 'harry_potter.jpg', 1, sysdate(), sysdate());

INSERT INTO film_genre(film_id, genre_id) VALUES
 (1,1),
 (1,6),
 (2,2),
 (3,2),
 (4,2),
 (4,7),
 (5,2),
 (5,6),
 (6,2),
 (7,2),
 (8,2),
 (8,3);

-- DISC
INSERT INTO disc(id, disc_format, in_stock, film_id, date_created, last_updated) VALUES
  (1, 'Blu-Ray', true, 1, sysdate(), sysdate()),
  (2, 'Blu-Ray', true, 1, sysdate(), sysdate()),
  (3, 'Blu-Ray', true, 2, sysdate(), sysdate()),
  (4, 'Blu-Ray', false, 2, sysdate(), sysdate()),
  (5, 'DVD', true, 1, sysdate(), sysdate()),
  (6, 'DVD', true, 2, sysdate(), sysdate()),
  (7, 'Blu-Ray', true, 3, sysdate(), sysdate()),
  (8, 'DVD', false, 4, sysdate(), sysdate()),
  (9, 'DVD', true, 5, sysdate(), sysdate()),
  (10, 'Blu-Ray', true, 6, sysdate(), sysdate()),
  (11, 'DVD', true, 7, sysdate(), sysdate()),
  (12, 'Blu-Ray', true, 8, sysdate(), sysdate());

-- USER WISHLIST
INSERT INTO wishlist(user_id, film_id, wished_on) VALUES
  (2, 1, SUBDATE(NOW(), 1)),
  (2, 2, SUBDATE(NOW(), 4)),
  (2, 5, SUBDATE(NOW(), 2)),
  (2, 8, SUBDATE(NOW(), 3));
