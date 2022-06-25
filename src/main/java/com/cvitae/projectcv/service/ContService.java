package com.cvitae.projectcv.service;

import com.cvitae.projectcv.dto.ContactAndPorfolioDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.model.ContactAndPortfolio;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ContService {
    public ContactAndPorfolioDtoPart createEntity(ContactAndPorfolioDtoPart contactAndPorfolioDtoPart);
    public ContactAndPorfolioDtoPart updateEntity(Long id, ContactAndPorfolioDtoPart contactAndPorfolioDtoPart);
    public MessageGeneral deleteById(Long id, HttpServletRequest request);
    public ContactAndPortfolio findEntityById(Long id);
    public ContactAndPorfolioDtoPart findDtoById(Long id);
    public List<ContactAndPorfolioDtoPart> findAll();
}
