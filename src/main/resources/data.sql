-- ADD SOME CITIES WITH COUNTRIES (I left the counties off)
INSERT INTO countries(id, iso, tld, name, date_created, last_updated) VALUES
	('GI', 'GI', '.gi', 'Gibraltar', now(), now()),
	('GK', 'GK', '.gk', 'Guernsey', now(), now()),
	('UK', 'GB', '.uk', 'United Kingdom', now(), now()),
	('US', 'US', '.us', 'United States of America', now(), now());

INSERT INTO cities(id, city, country_id, date_created, last_updated) VALUES
  (1, 'Milton Keynes', 'UK', now(), now()),
  (2, 'Great Yarmouth', 'UK', now(), now()),
  (3, 'Lincoln', 'UK', now(), now()),
  (4, 'London', 'UK', now(), now()),
  (5, 'Liverpool', 'UK', now(), now()),
  (6, 'Aylesbury', 'UK', now(), now()),
  (7, 'Aston Clinton', 'UK', now(), now()),
  (8, 'Gibraltar', 'GK', now(), now()),
  (9, 'Saint Peter Port', 'GI', now(), now());

-- ADD SOME USERS, ROLES AND ASSIGN ROLES
INSERT INTO users(id, email, username, password, enabled, date_created, last_updated) VALUES
  (1, 'paul.valle@example.com', 'pvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, sysdate(), sysdate()),
  (2, 'lorraine.valle@example.com', 'lvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, sysdate(), sysdate()),
  (3, 'dominic.valle@example.com', 'dvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, sysdate(), sysdate()),
  (4, 'jessica.valle@example.com', 'jvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, sysdate(), sysdate());

INSERT INTO roles(id, name, date_created, last_updated) VALUES
  (1, 'ADMIN', sysdate(), sysdate()),
  (2, 'USER', sysdate(), sysdate()),
  (3, 'UPLOADER', sysdate(), sysdate());

INSERT INTO user_role(user_id, role_id) VALUES
  (1,1),
  (2,2),
  (3,2),
  (4,3);