package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person dtoToEntity(PersonDtoPart personDtoPart){
        return new Person(null, personDtoPart.getFirstName(), personDtoPart.getLastName(), personDtoPart.getTypeProgramerName(), personDtoPart.getToolsName(), personDtoPart.getProfileDescription(), null, null, null, null, false, null, null);}
    public PersonDtoPart entityToDto(Person person){
        return new PersonDtoPart(person.getId(), person.getFirstName(), person.getLastName(), person.getTypeProgramerName(), person.getToolsName(), person.getProfileDescription(), person.getContactAndPortFolio(), person.getSkills(), person.getExperiences(), person.getEducation());
    }
}
