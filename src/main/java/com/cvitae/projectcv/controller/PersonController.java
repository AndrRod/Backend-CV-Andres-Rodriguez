package com.cvitae.projectcv.controller;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.model.Person;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonDtoPart createPerson(@RequestBody PersonDtoPart personDtoPart){
        return personService.createPerson(personDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public PersonDtoPart getPerson(){
        return personService.getPerson();
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public String deletePerson(HttpServletRequest request){
        return personService.deletePerson(request);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public PersonDtoPart updatePerson(@RequestBody PersonDtoPart personDtoPart){
        return personService.updatePerson(personDtoPart);
    }
}
