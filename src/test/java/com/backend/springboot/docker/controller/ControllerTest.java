package com.backend.springboot.docker.controller;

import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;
import com.backend.springboot.docker.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Controller test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    /**
     * The Mock mvc.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * The Person service.
     */
    @MockBean
    private PersonService personService;

    /**
     * The Person repository.
     */
    @MockBean
    private PersonRepository personRepository;


    /**
     * Gets person by job and salary test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getPersonByJobAndSalaryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getPersonByJobAndSalary")
                .param("job_name", "JobName1")
                .param("salary", "500")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

    /**
     * Gets person by entry date test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getPersonByEntryDateTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getPersonByEntryDate")
                .param("startDate", "2019-01-01")
                .param("endDate", "2019-02-01")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

    /**
     * Save person test.
     *
     * @throws Exception the exception
     */
    @Test
    public void savePersonTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .post("/savePerson")
                .content(JsonString(new Person()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }

    /**
     * Delete person test.
     *
     * @throws Exception the exception
     */
    @Test
    public void deletePersonTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.delete("/deletePerson")
                .param("id", "1"))
                .andExpect(status().isOk());


    }

    /**
     * Object as json.
     *
     * @param obj the obj
     * @return the string
     */
    public static String JsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
