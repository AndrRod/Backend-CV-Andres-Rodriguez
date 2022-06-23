package com.cvitae.projectcv.service;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;

public interface PersonService {
    public Person createPerson(PersonDtoPart personDtoPart);
    public Person updatePerson(PersonDtoPart personDtoPart);
    public Person getPerson();
    public void deletePerson();
}
