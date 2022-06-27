package com.cvitae.projectcv.controller;

import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "http://127.0.0.1:3000"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
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
    @GetMapping("/get")
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
