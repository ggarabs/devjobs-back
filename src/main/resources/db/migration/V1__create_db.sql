CREATE TABLE job (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    imagePath TEXT NOT NULL,
    mode TEXT NOT NULL,
    title TEXT NOT NULL,
    company TEXT NOT NULL,
    country TEXT NOT NULL,
    jobDescription TEXT NOT NULL,
    generalRequirements TEXT NOT NULL
);