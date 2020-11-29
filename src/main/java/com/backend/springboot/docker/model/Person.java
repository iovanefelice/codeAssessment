
package com.backend.springboot.docker.model;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * The type Person.
 */
@Entity(name="person")
@SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
@Data
public class Person {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @Column(name="id")
    private Long id;

    /**
     * The Name.
     */
    @Column(name="name")
    private String name;

    /**
     * The Entry date.
     */
    @Column(name="entry_date")
    private Date entry_date;

    /**
     * The Job.
     */
    @ManyToOne()
    @JoinColumn(name = "job_id")
    private Job job;



}
