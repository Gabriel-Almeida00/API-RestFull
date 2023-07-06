package com.gabriel.api.service;

import com.gabriel.api.data.vo.v1.PersonVO;
import com.gabriel.api.exceptions.ResourceNotFoundException;
import com.gabriel.api.model.Person;
import com.gabriel.api.repository.PersonRepository;
import com.gabriel.api.service.mapper.DozerMapper;
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
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObjetc(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("finding all people");
        return DozerMapper.parseListObjetc(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO personVo) {
        logger.info("Creating person");
        var entity = DozerMapper.parseObjetc(personVo, Person.class);
        var vo = DozerMapper.parseObjetc(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("update person");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObjetc(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("delete person");
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
}
