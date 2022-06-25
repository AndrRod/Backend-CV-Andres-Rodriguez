package com.cvitae.projectcv.service.serviceImpl;

import com.cvitae.projectcv.dto.ExperienceDtoPart;
import com.cvitae.projectcv.dto.mapper.ExperienceMapper;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.messagesHandler.NotFoundException;
import com.cvitae.projectcv.model.Experience;
import com.cvitae.projectcv.repository.ExperienceRepository;
import com.cvitae.projectcv.service.ExperienceService;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private ExperienceMapper experienceMapper;
    @Autowired
    private PersonService personService;
    @Override
    public ExperienceDtoPart createEntity(ExperienceDtoPart experienceDtoPart) {
        Experience experience = experienceRepository.save(experienceMapper.dtoToCreateEntity(experienceDtoPart));
        return experienceMapper.entityToDto(experience);
    }
    @Override
    public ExperienceDtoPart updateEntity(Long id, ExperienceDtoPart experienceDtoPart) {
        Experience experience = getEntityById(id);
        Optional.of(experience).stream().forEach((e)-> {
            if(experienceDtoPart.getTitle() != null) e.setTitle(experienceDtoPart.getTitle());
            if(experienceDtoPart.getDescription() != null) e.setDescription(experienceDtoPart.getDescription());
        });
        return experienceMapper.entityToDto(experienceRepository.save(experience));
    }
    @Override
    public List<ExperienceDtoPart> listOfDto() {
        return experienceMapper.listEntityToDto(experienceRepository.findAll());
    }
    @Override
    public ExperienceDtoPart getDtoById(Long id){
        return experienceMapper.entityToDto(getEntityById(id));
    }
    @Override
    public Experience getEntityById(Long id) {
        return experienceRepository.findById(id).orElseThrow(()-> new NotFoundException("experience was not found"));
    }

    @Override
    public MessageGeneral deleteById(Long id, HttpServletRequest request) {
        experienceRepository.delete(getEntityById(id));
        return new MessageGeneral("Was deleted succesful", request.getRequestURI());
    }
}
