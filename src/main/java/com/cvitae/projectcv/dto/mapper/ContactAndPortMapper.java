package com.cvitae.projectcv.dto.mapper;

import com.cvitae.projectcv.dto.ContactAndPorfolioDtoPart;
import com.cvitae.projectcv.model.ContactAndPortfolio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactAndPortMapper {
    public ContactAndPortfolio dtoToEntity(ContactAndPorfolioDtoPart contactAndPortfolio){
        return new ContactAndPortfolio(null, contactAndPortfolio.getLogoUrl(), contactAndPortfolio.getContactName(), contactAndPortfolio.getContactUrl());
    }
    public ContactAndPorfolioDtoPart entityToDto(ContactAndPortfolio contactAndPortfolio) {
        return new ContactAndPorfolioDtoPart(contactAndPortfolio.getId(), contactAndPortfolio.getLogoUrl(), contactAndPortfolio.getContactName(), contactAndPortfolio.getContactUrl());
    }
    public List<ContactAndPorfolioDtoPart> listEntityToDto(List<ContactAndPortfolio> contactAndPortfolioList){
        return contactAndPortfolioList.stream().map(e-> entityToDto(e)).collect(Collectors.toList());
    }
}
