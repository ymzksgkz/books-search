CREATE TABLE author (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_day DATE,
    comment VARCHAR(255)
);

CREATE TABLE book (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    overview VARCHAR(255),
    author_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
);
