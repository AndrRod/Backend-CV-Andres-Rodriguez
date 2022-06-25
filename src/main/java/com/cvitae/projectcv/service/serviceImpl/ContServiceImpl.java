package com.cvitae.projectcv.service.serviceImpl;

import com.cvitae.projectcv.dto.ContactAndPorfolioDtoPart;
import com.cvitae.projectcv.dto.mapper.ContactAndPortMapper;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.messagesHandler.NotFoundException;
import com.cvitae.projectcv.model.ContactAndPortfolio;
import com.cvitae.projectcv.repository.ContactAndPortfolioRepository;
import com.cvitae.projectcv.service.ContService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ContServiceImpl implements ContService {
    @Autowired
    public ContactAndPortfolioRepository contactAndPortfolioRepository;
    @Autowired
    public ContactAndPortMapper contactAndPortMapper;
    @Override
    public ContactAndPorfolioDtoPart createEntity(ContactAndPorfolioDtoPart contactAndPorfolioDtoPart) {
        ContactAndPortfolio contactAndPortfolio = contactAndPortfolioRepository.save(contactAndPortMapper.dtoToCreateEntity(contactAndPorfolioDtoPart));
        return contactAndPortMapper.entityToDto(contactAndPortfolio);
    }
    @Override
    public ContactAndPorfolioDtoPart updateEntity(Long id, ContactAndPorfolioDtoPart contactAndPorfolioDtoPart) {
        ContactAndPortfolio contactAndPortfolio = findEntityById(id);
        Optional.of(contactAndPortfolio).stream().forEach((c)->{
            if(contactAndPorfolioDtoPart.getLogoUrl() != null) c.setLogoUrl(contactAndPorfolioDtoPart.getLogoUrl());
            if(contactAndPorfolioDtoPart.getContactName()!= null) c.setContactName(contactAndPortfolio.getContactName());
            if(contactAndPorfolioDtoPart.getContactUrl()!= null) c.setContactUrl(contactAndPortfolio.getContactUrl());
        });
        return contactAndPortMapper.entityToDto(contactAndPortfolioRepository.save(contactAndPortfolio));
    }
    @Override
    public MessageGeneral deleteById(Long id, HttpServletRequest request) {
        contactAndPortfolioRepository.delete(findEntityById(id));
        return new MessageGeneral("the contact or portolio was deleted", request.getRequestURI());
    }
    @Override
    public ContactAndPortfolio findEntityById(Long id) {
        return contactAndPortfolioRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Contact or Porfolio was found"));
    }
    @Override
    public ContactAndPorfolioDtoPart findDtoById(Long id) {
        return contactAndPortMapper.entityToDto(findEntityById(id));
    }
    @Override
    public List<ContactAndPorfolioDtoPart> findAll() {
        return contactAndPortMapper.listEntityToDto(contactAndPortfolioRepository.findAll());
    }
}
