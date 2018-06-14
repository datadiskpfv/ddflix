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
  (4, 'Dummy 1', '1 Dummy Road', 'Dummy Estate', '', 4, 'DU123MY', now(), now()),
  (5, 'Dummy 2', '2 Dummy Road', 'Dummy Estate', '', 5, 'DU123MY', now(), now()),
  (6, 'Dummy 3', '3 Dummy Road', 'Dummy Estate', '', 6, 'DU123MY', now(), now()),
  (7, 'Dummy 4', '4 Dummy Road', 'Dummy Estate', '', 7, 'DU123MY', now(), now()),
  (8, 'Dummy 5', '5 Dummy Road', 'Dummy Estate', '', 8, 'DU123MY', now(), now()),
  (9, 'Dummy 5', '5 Dummy Road', 'Dummy Estate', '', 9, 'DU123MY', now(), now());

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