package com.cvitae.projectcv.service.serviceImpl;

import com.cvitae.projectcv.dto.EducationDtoPart;
import com.cvitae.projectcv.dto.mapper.EducationMapper;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.messagesHandler.NotFoundException;
import com.cvitae.projectcv.model.Education;
import com.cvitae.projectcv.repository.EducationRepository;
import com.cvitae.projectcv.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    public EducationRepository educationRepository;
    @Autowired
    public EducationMapper educationMapper;
    @Override
    public EducationDtoPart createEntity(EducationDtoPart educationDtoPart) {
        Education education = educationRepository.save(educationMapper.dtoToCreateEntity(educationDtoPart));
        return educationMapper.entityToDto(education);
    }
    @Override
    public EducationDtoPart updateEntity(Long id, EducationDtoPart educationDtoPart) {
        Education education = findEntityById(id);
        Optional.of(education).stream().forEach((e)->{
            if(educationDtoPart.getTitle()!= null) e.setTitle(educationDtoPart.getTitle());
            if(educationDtoPart.getDescription()!= null) e.setDescription(educationDtoPart.getDescription());
            if(educationDtoPart.getState() != null) e.setState(educationDtoPart.getState());
            if(educationDtoPart.getStartDate()!= null) e.setStartDate(educationDtoPart.getStartDate());
            if(educationDtoPart.getFinishDate()!= null) e.setFinishDate(educationDtoPart.getFinishDate());
        });
        return educationMapper.entityToDto(educationRepository.save(education));
    }
    @Override
    public MessageGeneral deleteEntityById(Long id, HttpServletRequest request) {
        educationRepository.delete(findEntityById(id));
        return new MessageGeneral("The Education was deleted", request.getRequestURI());
    }
    @Override
    public List<EducationDtoPart> listDtoEducations() {
        return educationMapper.listEntityToDto(educationRepository.findAll());
    }
    @Override
    public EducationDtoPart findById(Long id) {
        return educationMapper.entityToDto(findEntityById(id));
    }
    @Override
    public Education findEntityById(Long id) {
        return educationRepository.findById(id).orElseThrow(()-> new NotFoundException("Education not fund"));
    }
}
