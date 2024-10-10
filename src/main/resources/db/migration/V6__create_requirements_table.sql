CREATE TABLE requirements (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    description TEXT NOT NULL,
    job_id UUID NOT NULL,
    CONSTRAINT fk_job
    FOREIGN KEY (job_id)
    REFERENCES job(id)
    ON DELETE CASCADE
);
