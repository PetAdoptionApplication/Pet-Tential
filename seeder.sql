USE pettential;

INSERT INTO pet (age, breed, color, description, name, picture, sex, weight)
VALUES (3, 'German Shepherd', 'brown/black', 'great with kids', 'Axel', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Male', 45),
(1, 'Labrador', 'brown', 'loyal, affectionate', 'Bella', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg','Female', 35),
(5, 'Maltese', 'white', 'territorial, loving', 'Chloe', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Female', 12),
(8, 'Yorkie', 'brown', 'loving, attention seeker', 'buddy', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Male', 15),
(2, 'Great Dane', 'black', 'playful', 'Max', 'https://localnewsonly.com/wp-content/uploads/2018/12/dog-adoption-1.jpg', 'Male', 30);

INSERT INTO program (description, length, name, pet_type, time, type)
VALUES ('Teach pet to sit', 30, 'Sit Command', 'any', '10:00 am', 'voice, hand gesture');