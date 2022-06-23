package com.cvitae.projectcv.service.serviceImpl;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.dto.mapper.PersonMapper;
import com.cvitae.projectcv.model.Person;
import com.cvitae.projectcv.repository.PersonRepository;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;
    @Override
    public Person createPerson(PersonDtoPart personDtoPart) {
        if(!personRepository.findAll().isEmpty()) throw new RuntimeException("La persona ya existe");
        return personRepository.save(personMapper.dtoToEntity(personDtoPart));
    }
    @Override
    public Person updatePerson(PersonDtoPart personDtoPart) {
        Person person = getPerson();
        Optional.of(person).stream().forEach(
                (e)-> {
                if(personDtoPart.getFirstName() != null) e.setFirstName(personDtoPart.getFirstName());
                if(personDtoPart.getLastName() != null) e.setLastName(personDtoPart.getLastName());
                if(personDtoPart.getProfileDescription() != null)  e.setProfileDescription(personDtoPart.getProfileDescription());
                if(personDtoPart.getTypeProgramerName()!= null) e.setTypeProgramerName(personDtoPart.getTypeProgramerName());
                if(personDtoPart.getToolsName() != null) e.setToolsName(personDtoPart.getToolsName());
                }
        );
        return personRepository.save(person);
    }
    @Override
    public Person getPerson() {
        if(personRepository.findAll().isEmpty()) throw new RuntimeException("Error usuario no encontrado");
        return personRepository.findAll().get(0);
    }
    @Override
    public void deletePerson() {
        getPerson();
        personRepository.delete(getPerson());
    }
}
