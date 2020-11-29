package com.backend.springboot.docker.service.impl;


import com.backend.springboot.docker.exception.PersonException;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.ParseException;

import static org.mockito.Mockito.when;

/**
 * The type Person service impl test.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = PersonServiceImpl.class)
public class PersonServiceImplTest {

    /**
     * The Person repository.
     */
    @Mock
    PersonRepository personRepository;

    /**
     * The Person service.
     */
    @InjectMocks
    PersonServiceImpl personService;


    /**
     * Save person test.
     */
    @Test
    public void savePersonTest() throws PersonException {
        Person person = new Person();
        when(personRepository.save(person)).thenReturn(person);
        Person mySavedPerson = personService.savePerson(person);

        Assert.assertNotNull(mySavedPerson);
    }

    /**
     * Delete person test.
     */
    @Test
    public void deletePersonTest() throws PersonException {
        Long id = 1L;
        Mockito.doNothing().when(personRepository).deleteById(id);
        Boolean deleted = personService.deletePerson(id) ;

        Assert.assertNotNull(deleted);
    }

    /**
     * Gets person job and salary test.
     */
    @Test
    public void getPersonJobAndSalaryTest() throws PersonException {

        Assert.assertNotNull(personService.getPersonByJobAndSalary("JobName1",500D));
    }

    /**
     * Gets person by entry date test.
     *
     * @throws ParseException the parse exception
     */
    @Test
    public void getPersonByEntryDateTest() throws ParseException, PersonException {
        String startDate = "2019-01-01";
        String endDate = "2019-02-01";
        Assert.assertNotNull(personService.getPersonByEntryDate(startDate,endDate));
    }

    /**
     * On start up test.
     */
    @Test
    public void onStartUpTest()  {
       Assert.assertTrue(personService.onStartup(null));

    }



}
