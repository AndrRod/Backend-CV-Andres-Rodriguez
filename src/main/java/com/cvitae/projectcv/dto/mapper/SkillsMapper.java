package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.model.Skills;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillsMapper {
    public Skills dtoToEntity(SkillsDtoPart skillsPartDto){
        return new Skills(null, skillsPartDto.getName(), skillsPartDto.getDescription());
    }
    public SkillsDtoPart entityToDto(Skills skills){
        return new SkillsDtoPart(skills.getId(), skills.getName(), skills.getDescription());
    }
    public List<SkillsDtoPart> listEntityToDto(List<Skills> skillsList){
        return skillsList.stream().map(e-> entityToDto(e)).collect(Collectors.toList());
    }
}
