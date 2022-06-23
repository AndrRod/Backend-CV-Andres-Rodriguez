package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.ExperienceDtoPart;
import com.cvitae.projectcv.model.Experience;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExperienceMapper {
    public Experience dtoToEntity(ExperienceDtoPart experienceDtoPart){
        return new Experience(null, experienceDtoPart.getTitle(), experienceDtoPart.getDescription());
    }
    public ExperienceDtoPart entityToDto(Experience experience){
        return new ExperienceDtoPart(experience.getId(), experience.getTitle(), experience.getDescription());
    }
    public List<ExperienceDtoPart> listEntityToDto(List<Experience> experienceList){
        return experienceList.stream().map(e-> entityToDto(e)).collect(Collectors.toList());
    }
}
