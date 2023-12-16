 CREATE TABLE IF NOT EXISTS questions (
    id UUID PRIMARY KEY,
    image_url VARCHAR(255),
    question VARCHAR(255) NOT NULL,
    alternatives TEXT[] NOT NULL,
    correct_index INTEGER NOT NULL
 );