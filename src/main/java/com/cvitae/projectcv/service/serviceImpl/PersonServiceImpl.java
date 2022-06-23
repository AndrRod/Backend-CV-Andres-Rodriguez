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
    public PersonDtoPart createPerson(PersonDtoPart personDtoPart) {
        if(!personRepository.findAll().isEmpty()) throw new RuntimeException("Ya existe un perfil");
        return personMapper.entityToDto(personRepository.save(personMapper.dtoToEntity(personDtoPart)));
    }
    @Override
    public PersonDtoPart updatePerson(PersonDtoPart personDtoPart) {
        getPerson();
        Person person = personRepository.findAll().get(0);
        Optional.of(person).stream().forEach(
                (e)-> {
                if(personDtoPart.getFirstName() != null) e.setFirstName(personDtoPart.getFirstName());
                if(personDtoPart.getLastName() != null) e.setLastName(personDtoPart.getLastName());
                if(personDtoPart.getProfileDescription() != null)  e.setProfileDescription(personDtoPart.getProfileDescription());
                if(personDtoPart.getTypeProgramerName()!= null) e.setTypeProgramerName(personDtoPart.getTypeProgramerName());
                if(personDtoPart.getToolsName() != null) e.setToolsName(personDtoPart.getToolsName());
                }
        );
        return personMapper.entityToDto(personRepository.save(person));
    }
    @Override
    public PersonDtoPart getPerson() {
        if(personRepository.findAll().isEmpty()) throw new RuntimeException("Perfil no encontrado");
        return personMapper.entityToDto(personRepository.findAll().get(0));
    }
    @Override
    public void deletePerson() {
        getPerson();
        personRepository.delete(personRepository.findAll().get(0));
    }
}
