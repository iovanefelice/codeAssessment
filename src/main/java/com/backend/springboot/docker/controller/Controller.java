package com.backend.springboot.docker.controller;

import com.backend.springboot.docker.exception.PersonException;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

/**
 * The type Controller.
 */
@RestController
public class Controller {

    /**
     * The Person service.
     */
    @Autowired
    PersonService personService;


    /**
     * Save person.
     *
     * @param person the person
     * @return the response entity
     */
    @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
    public ResponseEntity<?> savePerson(@RequestBody Person person) {

        try {
            personService.savePerson(person);
            return new ResponseEntity<>("The user: " + person.getName() + " was succesfully added!", HttpStatus.CREATED);
        } catch (PersonException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }


    /**
     * Delete person by id.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/deletePerson", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@RequestParam Long id) {

        try {
            Boolean status = personService.deletePerson(id);
            return new ResponseEntity<>(status + ": Person was deleted succesfully", HttpStatus.OK);
        } catch (PersonException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }




    }


    /**
     * Gets person by job and salary.
     *
     * @param job_name the job name
     * @param salary   the salary
     * @return the person by job and salary
     */
    @RequestMapping(value = "/getPersonByJobAndSalary", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonByJobAndSalary(@RequestParam String job_name, @RequestParam Double salary) {

        try {
            List<Person> output = personService.getPersonByJobAndSalary(job_name, salary);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (PersonException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }



    }


    /**
     * Gets person list by entry date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the person by entry date
     * @throws ParseException the parse exception
     */
    @RequestMapping(value = "/getPersonByEntryDate", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonByEntryDate(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {

        try {
            List<Person> output = personService.getPersonByEntryDate(startDate, endDate);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (PersonException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }



    }
}
