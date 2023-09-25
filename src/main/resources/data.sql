INSERT INTO user_details(id, birth_date, name) values(10001, current_date(), 'Ranga');

INSERT INTO user_details(id, birth_date, name) values(10002, current_date(), 'Ravi');

INSERT INTO user_details(id, birth_date, name) values(10003, current_date(), 'Cristiano');

-- Post

INSERT INTO post(id, description, user_id) values (2001, 'I want to learn AWS', 10001);
INSERT INTO post(id, description, user_id) values (2002, 'I want to learn AWS', 10001);
INSERT INTO post(id, description, user_id) values (2003, 'I want to learn AWS', 10002);
INSERT INTO post(id, description, user_id) values (2004, 'I want to learn AWS', 10002);