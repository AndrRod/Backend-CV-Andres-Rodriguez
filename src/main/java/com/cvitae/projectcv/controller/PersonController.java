package com.cvitae.projectcv.controller;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.model.Person;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Person createPerson(@RequestBody PersonDtoPart personDtoPart){
        return personService.createPerson(personDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Person getPerson(){
        return personService.getPerson();
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public String deletePerson(){
        personService.deletePerson();
        return "the person was deleted";
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Person updatePerson(@RequestBody PersonDtoPart personDtoPart){
        return personService.updatePerson(personDtoPart);
    }
}
