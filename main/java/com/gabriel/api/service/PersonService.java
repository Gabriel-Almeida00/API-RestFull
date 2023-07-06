package com.gabriel.api.service;

import com.gabriel.api.data.vo.v1.PersonVO;
import com.gabriel.api.data.vo.v2.PersonVOv2;
import com.gabriel.api.exceptions.ResourceNotFoundException;
import com.gabriel.api.model.Person;
import com.gabriel.api.repository.PersonRepository;
import com.gabriel.api.service.mapper.Mapper;
import com.gabriel.api.service.mapper.custom.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return Mapper.parseObjetc(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("finding all people");
        return Mapper.parseListObjetc(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO personVo) {
        logger.info("Creating person");
        var entity = Mapper.parseObjetc(personVo, Person.class);
        var vo = Mapper.parseObjetc(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOv2 createv2(PersonVOv2 personVo) {
        logger.info("Creating person with v2");
        var entity = personMapper.convertVoToEntity(personVo);
        var vo = personMapper.convertEntityToVO(personRepository.save(entity));
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

        var vo = Mapper.parseObjetc(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("delete person");
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
}
