package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static java.util.stream.Collectors.toCollection;

@Component
public class PersonMapper {
    @Autowired
    private SkillsMapper skillsMapper;
    @Autowired
    private ExperienceMapper experienceMapper;
    @Autowired
    private EducationMapper educationMapper;
    @Autowired
    private ContactAndPortMapper contactAndPortMapper;

    public Person dtoToEntity(PersonDtoPart personDtoPart){
        return new Person(null, personDtoPart.getFirstName().toUpperCase(), personDtoPart.getLastName().toUpperCase(), personDtoPart.getTypeProgramerName().toUpperCase(), personDtoPart.getToolsName().toUpperCase(), personDtoPart.getProfileDescription(),null, null, null, null, false, null, null);}

    public PersonDtoPart entityToDto(Person pers){
        PersonDtoPart personDtoPart = new PersonDtoPart();
        Optional.of(personDtoPart).stream().forEach((person)->{
            person.setId(pers.getId());
            person.setFirstName(pers.getFirstName());
            person.setLastName(pers.getLastName());
            person.setTypeProgramerName(pers.getTypeProgramerName());
            person.setToolsName(pers.getToolsName());
            person.setProfileDescription(pers.getProfileDescription());
            if(pers.getContactAndPortfolio()!= null) person.setContactAndPortfolio(pers.getContactAndPortfolio().stream().map(e-> contactAndPortMapper.entityToDto(e)).collect(Collectors.toList()));
            if(pers.getSkills() != null) person.setSkills(pers.getSkills().stream().map(e-> skillsMapper.entityToDto(e)).collect(Collectors.toList()));
            if(pers.getExperiences() != null) person.setExperiences(pers.getExperiences().stream().map(e-> experienceMapper.entityToDto(e)).collect(Collectors.toList()));
            if(pers.getEducation()!=null) person.setEducation(pers.getEducation().stream().map(e-> educationMapper.entityToDto(e)).collect(Collectors.toList()));});
        return personDtoPart;
    }
}
