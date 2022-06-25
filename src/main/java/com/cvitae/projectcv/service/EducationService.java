package com.cvitae.projectcv.service;

import com.cvitae.projectcv.dto.EducationDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.model.Education;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EducationService {
    public EducationDtoPart createEntity(EducationDtoPart educationDtoPart);
    public EducationDtoPart updateEntity(Long id, EducationDtoPart educationDtoPart);
    public MessageGeneral deleteEntityById(Long id, HttpServletRequest request);
    public List<EducationDtoPart> listDtoEducations();
    public EducationDtoPart findById(Long id);
    public Education findEntityById(Long id);
}
