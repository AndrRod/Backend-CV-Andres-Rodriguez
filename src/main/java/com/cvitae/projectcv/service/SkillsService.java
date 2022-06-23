package com.cvitae.projectcv.service;


import com.cvitae.projectcv.dto.SkillsDtoPart;

import java.util.List;

public interface SkillsService {
    public SkillsDtoPart createSkill(SkillsDtoPart skillsDtoPart);
    public SkillsDtoPart findById(Long id);
    public SkillsDtoPart updateSkillById(Long id, SkillsDtoPart skillsDtoPart);
    public String deleteSkillById(Long id);
    public List<SkillsDtoPart> listSkillsDto();
}
