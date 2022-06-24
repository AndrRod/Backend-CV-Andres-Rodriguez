package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static java.util.stream.Collectors.toCollection;

@Component
public class PersonMapper {
    @Autowired
    private SkillsMapper skillsMapper;
    public Person dtoToEntity(PersonDtoPart personDtoPart){
        return new Person(null, personDtoPart.getFirstName().toUpperCase(), personDtoPart.getLastName().toUpperCase(), personDtoPart.getTypeProgramerName().toUpperCase(), personDtoPart.getToolsName().toUpperCase(), personDtoPart.getProfileDescription(), null, null, null, null, false, null, null);}
    public PersonDtoPart entityToDto(Person person){
        return new PersonDtoPart(
                person.getId(),
                person.getFirstName().toUpperCase(),
                person.getLastName().toUpperCase(),
                person.getTypeProgramerName().toUpperCase(),
                person.getToolsName().toUpperCase(),
                person.getProfileDescription(),
                person.getContactAndPortfolio(),
                person.getSkills().stream().map(e-> skillsMapper.entityToDto(e)).collect(Collectors.toList()),
                person.getExperiences(), person.getEducation());
    }
}
