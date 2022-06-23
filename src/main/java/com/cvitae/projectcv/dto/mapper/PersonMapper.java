package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person dtoToEntity(PersonDtoPart personDtoPart){
        return new Person(null, personDtoPart.getFirstName().toUpperCase(), personDtoPart.getLastName().toUpperCase(), personDtoPart.getTypeProgramerName().toUpperCase(), personDtoPart.getToolsName().toUpperCase(), personDtoPart.getProfileDescription(), null, null, null, null, false, null, null);}
    public PersonDtoPart entityToDto(Person person){
        return new PersonDtoPart(person.getId(), person.getFirstName().toUpperCase(), person.getLastName().toUpperCase(), person.getTypeProgramerName().toUpperCase(), person.getToolsName().toUpperCase(), person.getProfileDescription(), person.getContactAndPortfolio(), person.getSkills(), person.getExperiences(), person.getEducation());
    }
}
