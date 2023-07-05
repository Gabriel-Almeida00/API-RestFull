package com.gabriel.api.service;

import com.gabriel.api.model.Person;
import org.springframework.stereotype.Service;

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
}
