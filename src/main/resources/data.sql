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
INSERT INTO users(id, email, username, password, enabled, subscription, films_at_home_available, date_created, last_updated) VALUES
  (1, 'paul.valle@example.com', 'pvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, 0, 0, sysdate(), sysdate()),
  (2, 'lorraine.valle@example.com', 'lvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, 3, 3, sysdate(), sysdate()),
  (3, 'dominic.valle@example.com', 'dvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, 3, 3, sysdate(), sysdate()),
  (4, 'jessica.valle@example.com', 'jvalle', '$2a$10$Pd3q7s56Mxc/LjSNX0CIhuuHSf2pg8B0f2OoYX7kNxYYDJ8xtBsLW', true, 0,0, sysdate(), sysdate());

INSERT INTO roles(id, name, date_created, last_updated) VALUES
  (1, 'ADMIN', sysdate(), sysdate()),
  (2, 'USER', sysdate(), sysdate()),
  (3, 'UPLOADER', sysdate(), sysdate());

INSERT INTO user_role(user_id, role_id) VALUES
  (1,1),
  (2,2),
  (3,2),
  (4,3);

-- FILM, CLASSIFICATION and GENRE TEST DATA
INSERT INTO classifications(id, rating, image_name, date_created, last_updated) VALUES
  (1, 'U', 'U.png', sysdate(), sysdate()),
  (2, 'PG', 'PG.png', sysdate(), sysdate()),
  (3, '12', '12.png', sysdate(), sysdate()),
  (4, '15', '15.gif', sysdate(), sysdate()),
  (5, '18', '18.png', sysdate(), sysdate());

INSERT INTO genres(id, name, date_created, last_updated) VALUES
  (1, 'Horror', sysdate(), sysdate()),
  (2, 'Action', sysdate(), sysdate()),
  (3, 'Children', sysdate(), sysdate()),
  (4, 'Romance', sysdate(), sysdate()),
  (5, 'Thriller', sysdate(), sysdate()),
  (6, 'Sci-Fi', sysdate(), sysdate()),
  (7, 'Western', sysdate(), sysdate()),
  (8, 'War', sysdate(), sysdate()),
  (9, 'Comedy', sysdate(), sysdate());

-- DISC
-- INSERT INTO discs(id, disc_format, in_stock, film_id, faulty, lost, date_created, last_updated) VALUES
--   (1, 'Blu-Ray', true, 1, false, false, sysdate(), sysdate()),
--   (2, 'Blu-Ray', true, 1, false, false, sysdate(), sysdate()),
--   (3, 'DVD', true, 1, false, false, sysdate(), sysdate()),
--   (4, 'DVD', true, 1, false, false, sysdate(), sysdate()),
--   (5, 'Blu-Ray', true, 2, false, false, sysdate(), sysdate()),
--   (6, 'Blu-Ray', true, 2, false, false, sysdate(), sysdate()),
--   (7, 'Blu-Ray', true, 3, false, false, sysdate(), sysdate()),
--   (8, 'Blu-Ray', true, 4, false, false, sysdate(), sysdate()),
--   (9, 'Blu-Ray', true, 5, false, false, sysdate(), sysdate()),
--   (10, 'Blu-Ray', true, 6, false, false, sysdate(), sysdate()),
--   (11, 'Blu-Ray', true, 7, false, false, sysdate(), sysdate()),
--   (12, 'Blu-Ray', true, 8, false, false, sysdate(), sysdate()),
--   (13, 'Blu-Ray', true, 9, false, false, sysdate(), sysdate()),
--   (14, 'Blu-Ray', true, 10, false, false, sysdate(), sysdate()),
--   (15, 'Blu-Ray', true, 11, false, false, sysdate(), sysdate()),
--   (16, 'Blu-Ray', true, 12, false, false, sysdate(), sysdate()),
--   (17, 'Blu-Ray', true, 13, false, false, sysdate(), sysdate()),
--   (18, 'Blu-Ray', true, 14, false, false, sysdate(), sysdate()),
--   (19, 'Blu-Ray', true, 15, false, false, sysdate(), sysdate()),
--   (20, 'Blu-Ray', true, 16, false, false, sysdate(), sysdate()),
--   (21, 'Blu-Ray', true, 17, false, false, sysdate(), sysdate()),
--   (22, 'Blu-Ray', true, 18, false, false, sysdate(), sysdate()),
--   (23, 'Blu-Ray', true, 19, false, false, sysdate(), sysdate()),
--   (24, 'Blu-Ray', true, 20, false, false, sysdate(), sysdate()),
--   (25, 'Blu-Ray', true, 21, false, false, sysdate(), sysdate()),
--   (26, 'Blu-Ray', true, 22, false, false, sysdate(), sysdate()),
--   (27, 'Blu-Ray', true, 23, false, false, sysdate(), sysdate()),
--   (28, 'Blu-Ray', true, 24, false, false, sysdate(), sysdate()),
--   (29, 'Blu-Ray', true, 25, false, false, sysdate(), sysdate()),
--   (30, 'DVD', true, 6, false, false, sysdate(), sysdate()),
--   (31, 'DVD', true, 7, false, false, sysdate(), sysdate()),
--   (32, 'DVD', true, 8, false, false, sysdate(), sysdate()),
--   (33, 'DVD', true, 9, false, false, sysdate(), sysdate()),
--   (34, 'DVD', true, 10, false, false, sysdate(), sysdate()),
--   (35, 'DVD', true, 11, false, false, sysdate(), sysdate()),
--   (36, 'DVD', true, 12, false, false, sysdate(), sysdate()),
--   (37, 'DVD', true, 13, false, false, sysdate(), sysdate()),
--   (38, 'DVD', true, 14, false, false, sysdate(), sysdate()),
--   (39, 'DVD', true, 15, false, false, sysdate(), sysdate()),
--   (40, 'DVD', true, 16, false, false, sysdate(), sysdate()),
--   (41, 'DVD', true, 17, false, false, sysdate(), sysdate()),
--   (42, 'DVD', true, 18, false, false, sysdate(), sysdate()),
--   (43, 'DVD', true, 19, false, false, sysdate(), sysdate()),
--   (44, 'DVD', true, 20, false, false, sysdate(), sysdate()),
--   (45, 'DVD', true, 21, false, false, sysdate(), sysdate()),
--   (46, 'DVD', true, 22, false, false, sysdate(), sysdate()),
--   (47, 'DVD', true, 23, false, false, sysdate(), sysdate()),
--   (48, 'DVD', true, 24, false, false, sysdate(), sysdate()),
--   (49, 'DVD', true, 25, false, false, sysdate(), sysdate());
