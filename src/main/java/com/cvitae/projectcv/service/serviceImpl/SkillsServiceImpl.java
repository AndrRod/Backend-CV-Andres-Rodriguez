package com.cvitae.projectcv.service.serviceImpl;
import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.dto.mapper.SkillsMapper;
import com.cvitae.projectcv.model.Skills;
import com.cvitae.projectcv.repository.PersonRepository;
import com.cvitae.projectcv.repository.SkillsRepository;
import com.cvitae.projectcv.service.PersonService;
import com.cvitae.projectcv.service.SkillsService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Skills skill = skillsMapper.dtoToEntity(skillsDtoPart);
        personService.getPersonEntity().getSkills().add(skillRepository.save(skill));
        return skillsMapper.entityToDto(skillRepository.save(skill));
    }
    @Override
    public SkillsDtoPart findById(Long id) {
        return skillsMapper.entityToDto(findEntityById(id));
    }
    @Override
    public Skills findEntityById(Long id){
        return skillRepository.findById(id).orElseThrow(()-> new RuntimeException("Skills not found"));
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
    public String deleteSkillById(Long id) {
        skillRepository.delete(findEntityById(id));
        return "The skill was delete";
    }
    @Override
    public Collection<SkillsDtoPart> listSkillsDto() {
        return skillsMapper.listEntityToDto(skillRepository.findAll());
    }
}
