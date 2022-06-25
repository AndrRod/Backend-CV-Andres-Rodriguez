package com.cvitae.projectcv.service;

import com.cvitae.projectcv.dto.ExperienceDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.model.Experience;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExperienceService {
    public ExperienceDtoPart createEntity(ExperienceDtoPart experienceDtoPart);
    public ExperienceDtoPart updateEntity(Long id, ExperienceDtoPart experienceDtoPart);
    public List<ExperienceDtoPart> listOfDto();
    public ExperienceDtoPart getDtoById(Long id);
    public Experience getEntityById(Long id);
    public MessageGeneral deleteById(Long id, HttpServletRequest request);
}
