package com.gabriel.api.service;

import com.gabriel.api.data.vo.v1.PersonVO;
import com.gabriel.api.exceptions.ResourceNotFoundException;
import com.gabriel.api.model.Person;
import com.gabriel.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<PersonVO> findAll() {
        logger.info("finding all people");
        return personRepository.findAll();
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating person");
        return personRepository.save(person);
    }

    public PersonVO update(PersonVO person) {
        logger.info("update person");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("delete person");
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
}
