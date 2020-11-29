ALTER TABLE person ADD job_id int2 NOT NULL;
ALTER TABLE person ADD CONSTRAINT person_fk FOREIGN KEY (job_id) REFERENCES job(id);;
