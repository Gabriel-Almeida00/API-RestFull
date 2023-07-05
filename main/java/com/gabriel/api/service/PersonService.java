package com.gabriel.api.service;

import com.gabriel.api.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");
        Person person =  new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel");
        person.setLastName("Costa");
        person.setAddress("Uberlândia - Minas gerais, Brasil");
        person.setGender("male");
        return person;
    }

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person create(Person person){
        logger.info("Creating person");
        return person;
    }

    public Person update(Person person){
        logger.info("update person");
        return person;
    }

    public void delete(String id){
        logger.info("delete person");
    }

    private Person mockPerson(int i) {
        logger.info("Finding all people!");
        Person person =  new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name :" + i);
        person.setLastName("last name " + i);
        person.setAddress("address " + i);
        person.setGender("gender " + i);
        return person;
    }
}
