package com.backend.springboot.docker.repository;


import com.backend.springboot.docker.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * The interface Person repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    /**
     * Find persons by job and salary.
     *
     * @param job_name the job name
     * @param salary   the salary
     * @return the list
     */
    @Query("select p from person p where p.job.job_name = ?1 and p.job.salary <= ?2")
    List<Person> findPersonsByJob(String job_name, Double salary);

    /**
     * Find persons by entry date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    @Query("select p from person p where p.entry_date BETWEEN ?1 AND ?2")
    List<Person> findPersonsByEntryDate(Date startDate, Date endDate);


}
