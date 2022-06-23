package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.EducationDtoPart;
import com.cvitae.projectcv.model.Education;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EducationMapper {
    public Education dtoToEntity(EducationDtoPart educationDtoPart){
        return new Education(null, educationDtoPart.getTitle(), educationDtoPart.getDescription(), educationDtoPart.getState(), educationDtoPart.getStartDate(), educationDtoPart.getFihishDate());
    }
    public EducationDtoPart entityToDto(Education education){
        return new EducationDtoPart(education.getId(), education.getTitle(), education.getDescription(), education.getState(), education.getStartDate(), education.getFihishDate());
    }
    public List<EducationDtoPart> listEntityToDto(List<Education> educationList){
        return educationList.stream().map(e-> entityToDto(e)).collect(Collectors.toList());
    }
}
