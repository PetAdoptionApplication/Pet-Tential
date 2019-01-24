USE pettential;

INSERT INTO pet (age, breed, color, description, name, sex, weight)
VALUES (3, 'German Shepherd', 'brown/black', 'great with kids', 'Axel', 'Male', 45),
(1, 'Labrador', 'brown', 'loyal, affectionate', 'Bella', 'Female', 35),
(5, 'Maltese', 'white', 'territorial, loving', 'Chloe', 'Female', 12),
(8, 'Yorkie', 'brown', 'loving, attention seeker', 'buddy', 'Male', 15),
(2, 'Great Dane', 'black', 'playful', 'Max', 'Male', 30);

INSERT INTO program (description, length, name, pet_type, time, type)
VALUES ('Teach pet to sit', 30, 'Sit Command', 'any', '10:00 am', 'voice, hand gesture');