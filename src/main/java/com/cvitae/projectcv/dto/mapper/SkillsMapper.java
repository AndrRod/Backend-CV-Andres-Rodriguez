package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.model.Skills;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class SkillsMapper {
    @Autowired
    private PersonService personService;

    public Skills dtoToEntity(SkillsDtoPart skillsPartDto){
        return new Skills(null, skillsPartDto.getName(), skillsPartDto.getDescription(), personService.getPersonEntity());
    }
    public SkillsDtoPart entityToDto(Skills skills){
        return new SkillsDtoPart(skills.getId(), skills.getName(), skills.getDescription());
    }
    public Collection<SkillsDtoPart> listEntityToDto(Collection<Skills> skillsList){
        return skillsList.stream().map(e-> entityToDto(e)).collect(Collectors.toList());
    }
}
