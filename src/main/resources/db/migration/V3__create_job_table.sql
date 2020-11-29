DROP TABLE IF EXISTS JOB;

CREATE TABLE JOB (
	id serial NOT NULL PRIMARY KEY,
	job_name varchar NULL,
	job_description varchar NULL,
	salary numeric(7,2) NULL
);
GRANT SELECT, INSERT ON JOB TO ${user};