CREATE TABLE companies (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    company_name TEXT NOT NULL,
    website TEXT NOT NULL,
    image_path TEXT NOT NULL,
    large_image_path TEXT NOT NULL
);