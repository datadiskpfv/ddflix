-- Insert country data
INSERT INTO country(id, iso, tld, name, date_created, last_updated) VALUES
	('GI', 'GI', '.gi', 'Gibraltar', now(), now()),
	('GK', 'GK', '.gk', 'Guernsey', now(), now()),
	('UK', 'GB', '.uk', 'United Kingdom', now(), now());

INSERT INTO city(id, city, country_id, date_created, last_updated) VALUES
  (1, 'Milton Keynes', 'UK', now(), now());