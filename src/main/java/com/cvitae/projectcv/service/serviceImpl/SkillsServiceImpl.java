package com.cvitae.projectcv.service.serviceImpl;
import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.dto.mapper.SkillsMapper;
import com.cvitae.projectcv.model.Skills;
import com.cvitae.projectcv.repository.SkillsRepository;
import com.cvitae.projectcv.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {
    @Autowired
    private SkillsRepository repository;
    @Autowired
    private SkillsMapper skillsMapper;
    @Override
    public SkillsDtoPart createSkill(SkillsDtoPart skillsDtoPart) {
        return skillsMapper.entityToDto(repository.save(skillsMapper.dtoToEntity(skillsDtoPart)));
    }
    @Override
    public SkillsDtoPart findById(Long id) {
        return skillsMapper.entityToDto(repository.findById(id).orElseThrow(()-> new RuntimeException("Skills not found")));
    }
    @Override
    public SkillsDtoPart updateSkillById(Long id, SkillsDtoPart skillsDtoPart) {
        findById(id);
        Optional<Skills> skill = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RuntimeException("Skills not found")));
        skill.stream().forEach((e)-> {
            if(skillsDtoPart.getName() != null) e.setName(skillsDtoPart.getName());
            if(skillsDtoPart.getDescription() != null) e.setDescription(skillsDtoPart.getDescription());
        });
        return skillsMapper.entityToDto(repository.save(skill.get()));
    }
    @Override
    public String deleteSkillById(Long id) {
        repository.delete(repository.findById(id).orElseThrow(()-> new RuntimeException("Skills not found")));
        return "The skill was delete";
    }
    @Override
    public List<SkillsDtoPart> listSkillsDto() {
        return skillsMapper.listEntityToDto(repository.findAll());
    }
}
