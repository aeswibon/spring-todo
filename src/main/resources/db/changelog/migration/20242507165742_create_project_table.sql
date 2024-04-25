CREATE TABLE IF NOT EXISTS project (
    uuid UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP
);
