package com.cvitae.projectcv.service;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;

public interface PersonService {
    public PersonDtoPart createPerson(PersonDtoPart personDtoPart);
    public PersonDtoPart updatePerson(PersonDtoPart personDtoPart);
    public PersonDtoPart getPerson();
    public Person getPersonEntity();
    public void deletePerson();
}
