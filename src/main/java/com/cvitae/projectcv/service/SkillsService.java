package com.cvitae.projectcv.service;


import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.model.Skills;

import java.util.Collection;

public interface SkillsService {
    public SkillsDtoPart createSkill(SkillsDtoPart skillsDtoPart);
    public SkillsDtoPart findById(Long id);
    public Skills findEntityById(Long id);
    public SkillsDtoPart updateSkillById(Long id, SkillsDtoPart skillsDtoPart);
    public String deleteSkillById(Long id);
    public Collection<SkillsDtoPart> listSkillsDto();
}
