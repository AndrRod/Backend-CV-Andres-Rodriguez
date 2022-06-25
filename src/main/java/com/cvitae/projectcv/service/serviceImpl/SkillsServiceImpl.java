package com.cvitae.projectcv.service.serviceImpl;
import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.dto.mapper.SkillsMapper;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.messagesHandler.NotFoundException;
import com.cvitae.projectcv.model.Skills;
import com.cvitae.projectcv.repository.SkillsRepository;
import com.cvitae.projectcv.service.PersonService;
import com.cvitae.projectcv.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {
    @Autowired
    private SkillsRepository skillRepository;
    @Autowired
    private SkillsMapper skillsMapper;
    @Autowired
    private PersonService personService;

    @Override
    public SkillsDtoPart createSkill(SkillsDtoPart skillsDtoPart) {
        Skills skill = skillRepository.save(skillsMapper.dtoToCreateEntity(skillsDtoPart));
        return skillsMapper.entityToDto(skill);
    }
    @Override
    public SkillsDtoPart findById(Long id) {
        return skillsMapper.entityToDto(findEntityById(id));
    }
    @Override
    public Skills findEntityById(Long id){
        return skillRepository.findById(id).orElseThrow(()-> new NotFoundException("Skills not found"));
    }
    @Override
    public SkillsDtoPart updateSkillById(Long id, SkillsDtoPart skillsDtoPart) {
        Optional<Skills> skill = Optional.of(findEntityById(id));
        skill.stream().forEach((e)-> {
            if(skillsDtoPart.getName() != null) e.setName(skillsDtoPart.getName());
            if(skillsDtoPart.getDescription() != null) e.setDescription(skillsDtoPart.getDescription());
        });
        return skillsMapper.entityToDto(skillRepository.save(skill.get()));
    }
    @Override
    public MessageGeneral deleteSkillById(Long id, HttpServletRequest request) {
        skillRepository.delete(findEntityById(id));
        return new MessageGeneral("The skill was delete", request.getRequestURI());
    }
    @Override
    public Collection<SkillsDtoPart> listSkillsDto() {
        return skillsMapper.listEntityToDto(skillRepository.findAll());
    }
}
