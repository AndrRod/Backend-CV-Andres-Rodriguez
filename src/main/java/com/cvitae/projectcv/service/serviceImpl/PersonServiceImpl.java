package com.cvitae.projectcv.service.serviceImpl;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.dto.mapper.PersonMapper;
import com.cvitae.projectcv.messagesHandler.BadRequestException;
import com.cvitae.projectcv.messagesHandler.NotFoundException;
import com.cvitae.projectcv.model.Person;
import com.cvitae.projectcv.repository.PersonRepository;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Lazy
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;
    @Override
    public PersonDtoPart createPerson(PersonDtoPart personDtoPart) {
        if(!personRepository.findAll().isEmpty()) throw new BadRequestException("Ya existe un perfil");
        return personMapper.entityToDto(personRepository.save(personMapper.dtoToEntity(personDtoPart)));
    }
    @Override
    public PersonDtoPart updatePerson(PersonDtoPart personDtoPart) {
        Person person = getPersonEntity();
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
        return personMapper.entityToDto(getPersonEntity());
    }

    @Override
    public Person getPersonEntity() {
        if(personRepository.findAll().isEmpty()) throw new NotFoundException("Perfil no encontrado");
        return personRepository.findAll().stream().findFirst().get();
    }

    @Override
    public String deletePerson(HttpServletRequest request){
        personRepository.delete(getPersonEntity());
            return "the perfil was deleted succesful";
    }
}
