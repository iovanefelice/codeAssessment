package com.backend.springboot.docker.model;

import lombok.Data;
import javax.persistence.*;


/**
 * The type Job.
 */
@Entity(name = "job")
@SequenceGenerator(name = "job_id_seq", sequenceName = "job_id_seq", allocationSize = 1)
@Data
public class Job {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id_seq")
    @Column(name="id")
    private Long id;

    /**
     * The Job name.
     */
    @Column(name="job_name")
    private String job_name;

    /**
     * The Job description.
     */
    @Column(name="job_description")
    private String job_description;

    /**
     * The Salary.
     */
    @Column(name="salary")
    private Double salary;

}
