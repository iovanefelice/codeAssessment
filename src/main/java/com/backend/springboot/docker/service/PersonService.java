package com.backend.springboot.docker.service;

import com.backend.springboot.docker.exception.PersonException;
import com.backend.springboot.docker.model.Person;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Person service.
 */
@Service
public interface PersonService {
    /**
     * Save user person.
     *
     * @param person the person
     * @return the person
     */
    Person savePerson(Person person) throws PersonException;

    /**
     * Delete person boolean.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deletePerson(Long id) throws PersonException;

    /**
     * Gets person by job and salary.
     *
     * @param job_name the job name
     * @param salary   the salary
     * @return the person by job and salary
     */
    List<Person> getPersonByJobAndSalary(String job_name, Double salary) throws PersonException;

    /**
     * Retrieves PERSON given an entry date
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the person by entry date
     * @throws ParseException the parse exception
     */
    List<Person> getPersonByEntryDate(String startDate, String endDate) throws ParseException, PersonException;

    /**
     * Logs on the screen PERSON with biggest salaries.
     *
     * @param event the event
     * @return the boolean
     */
    @EventListener
    Boolean onStartup(ApplicationReadyEvent event);
}
