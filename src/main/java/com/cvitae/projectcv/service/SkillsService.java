package com.cvitae.projectcv.service;


import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.model.Skills;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public interface SkillsService {
    public SkillsDtoPart createSkill(SkillsDtoPart skillsDtoPart);
    public SkillsDtoPart findById(Long id);
    public Skills findEntityById(Long id);
    public SkillsDtoPart updateSkillById(Long id, SkillsDtoPart skillsDtoPart);
    public MessageGeneral deleteSkillById(Long id, HttpServletRequest request);
    public Collection<SkillsDtoPart> listSkillsDto();
}
