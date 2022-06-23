package com.cvitae.projectcv.service;

import com.cvitae.projectcv.dto.PersonDtoPart;

public interface PersonService {
    public PersonDtoPart createPerson(PersonDtoPart personDtoPart);
    public PersonDtoPart updatePerson(PersonDtoPart personDtoPart);
    public PersonDtoPart getPerson();
    public void deletePerson();
}
