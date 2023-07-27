package com.gabriel.api.integrationTests.repositories;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabriel.api.integrationTests.testContainers.AbstractIntegrationTest;
import com.gabriel.api.model.Person;
import com.gabriel.api.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class PersonRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    private static Person person;

    @BeforeAll
    public static void setup(){
        person = new Person();
    }

    @Test
    @Order(1)
    public void testFindByName() throws JsonMappingException, JsonProcessingException {

        Pageable pageable = PageRequest.of(0,6, Sort.by(Sort.Direction.ASC,"firstName"));
        person = personRepository.findPersonByName("a",pageable).getContent().get(0);

        assertNotNull(person.getId());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
        assertNotNull(person.getAddress());
        assertNotNull(person.getGender());
        assertTrue(person.getEnabled());

        assertEquals(68, person.getId());

        assertEquals("Abra", person.getFirstName());
        assertEquals("Doyle", person.getLastName());
        assertEquals("82780 Amoth Street", person.getAddress());
        assertEquals("Female", person.getGender());
    }

    @Test
    @Order(2)
    public void testDisablePerson() throws JsonMappingException, JsonProcessingException {

        personRepository.disablePerson(person.getId());
        Pageable pageable = PageRequest.of(0,6, Sort.by(Sort.Direction.ASC,"firstName"));
        person = personRepository.findPersonByName("a",pageable).getContent().get(0);

        assertNotNull(person.getId());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
        assertNotNull(person.getAddress());
        assertNotNull(person.getGender());
        assertFalse(person.getEnabled());

        assertEquals(68, person.getId());

        assertEquals("Abra", person.getFirstName());
        assertEquals("Doyle", person.getLastName());
        assertEquals("82780 Amoth Street", person.getAddress());
        assertEquals("Female", person.getGender());
    }
}
