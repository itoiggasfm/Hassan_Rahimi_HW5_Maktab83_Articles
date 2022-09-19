CREATE DATABASE Article;

create table User 
(
    id serial PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    national_code VARCHAR,
    birthday VARCHAR
);
CREATE Table Articles
(
    id serial PRIMARY KEY,
    title VARCHAR,
    brief TEXT,
    content TEXT,
    is_published BOOLEAN,
    user_id INT,
        CONSTRAINT fk_userid
            Foreign Key (user_id)
                REFERENCES User(id)
);