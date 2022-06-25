package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.EducationDtoPart;
import com.cvitae.projectcv.model.Education;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EducationMapper {
    @Autowired
    private PersonService personService;
    public Education dtoToCreateEntity(EducationDtoPart educationDtoPart){
        return new Education(null, educationDtoPart.getTitle(), educationDtoPart.getDescription(), educationDtoPart.getState(), educationDtoPart.getStartDate(), educationDtoPart.getFinishDate(), personService.getPersonEntity());
    }
    public EducationDtoPart entityToDto(Education education){
        return new EducationDtoPart(education.getId(), education.getTitle(), education.getDescription(), education.getState(), education.getStartDate(), education.getFinishDate());
    }
    public List<EducationDtoPart> listEntityToDto(List<Education> educationList){
        return educationList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
