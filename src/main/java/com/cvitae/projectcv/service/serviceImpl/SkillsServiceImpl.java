package com.cvitae.projectcv.service.serviceImpl;
import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.dto.mapper.SkillsMapper;
import com.cvitae.projectcv.model.Skills;
import com.cvitae.projectcv.repository.PersonRepository;
import com.cvitae.projectcv.repository.SkillsRepository;
import com.cvitae.projectcv.service.PersonService;
import com.cvitae.projectcv.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {
    @Autowired
    private SkillsRepository repository;
    @Autowired
    private SkillsMapper skillsMapper;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Override
    public SkillsDtoPart createSkill(SkillsDtoPart skillsDtoPart) {
        Skills skill = skillsMapper.dtoToEntity(skillsDtoPart);
        personService.getPerson().getSkills().add(skill);
        return skillsMapper.entityToDto(repository.save(skill));
    }
    @Override
    public SkillsDtoPart findById(Long id) {
        return skillsMapper.entityToDto(findEntityById(id));
    }
    @Override
    public Skills findEntityById(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Skills not found"));
    }
    @Override
    public SkillsDtoPart updateSkillById(Long id, SkillsDtoPart skillsDtoPart) {
        Optional<Skills> skill = Optional.of(findEntityById(id));
        skill.stream().forEach((e)-> {
            if(skillsDtoPart.getName() != null) e.setName(skillsDtoPart.getName());
            if(skillsDtoPart.getDescription() != null) e.setDescription(skillsDtoPart.getDescription());
        });
        return skillsMapper.entityToDto(repository.save(skill.get()));
    }
    @Override
    public String deleteSkillById(Long id) {
        repository.delete(findEntityById(id));
        return "The skill was delete";
    }
    @Override
    public List<SkillsDtoPart> listSkillsDto() {
        return skillsMapper.listEntityToDto(repository.findAll());
    }
}
