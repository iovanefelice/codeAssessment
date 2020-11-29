package com.backend.springboot.docker.service.impl;

import com.backend.springboot.docker.exception.PersonException;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;
import com.backend.springboot.docker.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * The type Person service.
 */
public class PersonServiceImpl implements PersonService {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    /**
     * The Person repository.
     */
    @Autowired
    PersonRepository personRepository;

    /**
     * Save person.
     *
     * @param person the person
     * @return the person
     */
    @Override
    public Person savePerson(Person person) throws PersonException {

        try {
            return personRepository.save(person);

        } catch (Exception e) {
            throw  new PersonException("Error creating the user, please try again later "+e.getMessage());
        }




    }

    /**
     * Delete user by id.
     *
     * @param id the id
     * @return the boolean
     */
    @Override
    public Boolean deletePerson(Long id) throws PersonException {
        try {
            personRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new PersonException("Error, user cant be deleted "+e.getMessage());
        }


    }

    /**
     * Gets person by job and salary.
     *
     * @param job_name the job name
     * @param salary   the salary
     * @return the person by job and salary
     */
    @Override
    public List<Person> getPersonByJobAndSalary(String job_name, Double salary) throws PersonException {
        try {
            return personRepository.findPersonsByJob(job_name, salary);
        } catch (Exception e) {
            throw new PersonException("Error retrieving PERSON from DB: "+e.getMessage());
        }

    }

    /**
     * Retrieves PERSON given an entry date
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the person by entry date
     *
     */
    @Override
    public List<Person> getPersonByEntryDate(String startDate, String endDate) throws PersonException {
        try {
            Date sDate = convertIntoDate(startDate);
            Date eDate = convertIntoDate(endDate);
            return personRepository.findPersonsByEntryDate(sDate, eDate);
        } catch (Exception e) {
            throw new PersonException("Error retrieving PERSON from DB: "+e.getMessage());
        }

    }


    /**
     * Logs on the screen PERSON with biggest salaries.
     *
     * @param event the event
     * @return the boolean
     */
    @Override
    public Boolean onStartup(ApplicationReadyEvent event) {
        try {
            List<Person> allPerson = personRepository.findAll();
            Map<String, List<Person>> groupByJobNameMap =
                    allPerson.stream().collect(Collectors.groupingBy(a -> a.getJob().getJob_name()));

            groupByJobNameMap.forEach((k, v) ->
                    {
                        Person bestEmployee = Collections.max(v, Comparator.comparingDouble(a -> a.getJob().getSalary()));
                        logger.info(
                                "Employee name: " + bestEmployee.getName() + " Position: " + bestEmployee.getJob().getJob_name() + " Salary" + bestEmployee.getJob().getSalary());

                    }
            );
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    /**
     * Convert into date date.
     * Converts a String date into date
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    private Date convertIntoDate(String date) throws Exception {
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }catch(Exception e){
            throw new Exception("Error parsing the date, please use a correct format YYYY-MM-DD"+e.getMessage());
        }

    }
}
