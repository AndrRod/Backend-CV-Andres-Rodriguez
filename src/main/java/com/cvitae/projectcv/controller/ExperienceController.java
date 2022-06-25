package com.cvitae.projectcv.controller;

import com.cvitae.projectcv.dto.ExperienceDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/experience")
@RestController
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ExperienceDtoPart createEntity(@RequestBody ExperienceDtoPart experienceDtoPart){
        return experienceService.createEntity(experienceDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ExperienceDtoPart> listDto(){
        return experienceService.listOfDto();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ExperienceDtoPart getById(@PathVariable Long id){
        return experienceService.getDtoById(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ExperienceDtoPart updateEntity(@RequestBody ExperienceDtoPart experienceDtoPart, @PathVariable Long id){
        return experienceService.updateEntity(id, experienceDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public MessageGeneral deleteById(@PathVariable Long id, HttpServletRequest request){
        return experienceService.deleteById(id, request);
    }
}
