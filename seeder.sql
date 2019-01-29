USE pettential;

INSERT into user (name, number, password, username, address, email, is_shelter)
values ('KC', '325-998-4721', '$2a$10$2C0uxzhqZpk8doeIi2fH/eIfj8Xt6.t9RPd9LZRdaJ6nWK64MA8ma', 'kmac1992', '302 Chicago Blvd, San Antonio, Texas', 'kc@email.com', 0),
('Shelter', '325-998-4721', '$2a$10$2C0uxzhqZpk8doeIi2fH/eIfj8Xt6.t9RPd9LZRdaJ6nWK64MA8ma', 'shelter', '302 Chicago Blvd, San Antonio, Texas', 'shelter@email.com', 1);

INSERT into shelter (name, number, location, email, user_id, number_of_pets)
values ('The Good Place', '325-998-4721', '302 Chicago Blvd, San Antonio, Texas', 'kc@email.com', 3, 3);

INSERT INTO pet (age, breed, color, description, name, picture, sex, weight, shelter_id)
VALUES (3, 'German Shepherd', 'brown/black', 'great with kids', 'Axel', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Male', 45, 1),
(1, 'Labrador', 'brown', 'loyal, affectionate', 'Bella', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg','Female', 35, 1),
(5, 'Maltese', 'white', 'territorial, loving', 'Chloe', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Female', 12, 1),
(8, 'Yorkie', 'brown', 'loving, attention seeker', 'buddy', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Male', 15, 1),
(2, 'Great Dane', 'black', 'playful', 'Max', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Male', 30, 1);

INSERT INTO program (description, length, name, pet_type, time, type, shelter_id)
VALUES ('Teach pet to sit', .5, 'Sit Command', 'any', '10:00 am', 'voice, hand gesture', 1),
('Walk a dog', 1, 'Walk your dog', 'Dog', '11:00 am', 'Dog Walking', 1),
('How to Train your Dragon', 2, 'Dragon Training', 'Dragon', '3:00 pm', 'Dragon Training', 1);

INSERT INTO volunteer (date, time, shel_id, number_of_vols)
VALUES ('02/12/2019', '3:00 pm', 1, 0),
('02/14/2019', '10:30 am', 1, 0);

INSERT INTO app (pet_id, shelter_id, user_id)
VALUES (2, 1, 2),
(4, 1, 2);