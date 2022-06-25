package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.ExperienceDtoPart;
import com.cvitae.projectcv.model.Experience;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExperienceMapper {
    @Autowired
    private PersonService personService;

    public Experience dtoToCreateEntity(ExperienceDtoPart experienceDtoPart){
        return new Experience(null, experienceDtoPart.getTitle(), experienceDtoPart.getDescription(), personService.getPersonEntity());
    }
    public ExperienceDtoPart entityToDto(Experience experience){
        return new ExperienceDtoPart(experience.getId(), experience.getTitle(), experience.getDescription());
    }
    public List<ExperienceDtoPart> listEntityToDto(List<Experience> experienceList){
        return experienceList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
