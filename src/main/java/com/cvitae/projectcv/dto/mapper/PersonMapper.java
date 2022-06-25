package com.cvitae.projectcv.dto.mapper;
import com.cvitae.projectcv.dto.PersonDtoCreate;
import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.stream.Collectors;

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
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    public Person dtoToEntity(PersonDtoPart personDtoPart){
        return new Person(null, personDtoPart.getFirstName().toUpperCase(), personDtoPart.getLastName().toUpperCase(), personDtoPart.getTypeProgramerName().toUpperCase(), personDtoPart.getToolsName().toUpperCase(), personDtoPart.getProfileDescription(),null, null, null, null, false, null, null);}

    public Person dtoToCreateEntity(PersonDtoCreate personDtoCreate){
        Person person = new Person();
        person.setEmail(personDtoCreate.getEmail());
//        person.setPassword(passwordEncoder.encode(personDtoCreate.getPassword()));
        person.setPassword(personDtoCreate.getPassword());
        return person;
    }
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
