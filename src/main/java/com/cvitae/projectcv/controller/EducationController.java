package com.cvitae.projectcv.controller;


import com.cvitae.projectcv.dto.EducationDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:3000"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@RestController
@RequestMapping("/education")
public class EducationController {
    @Autowired
    private EducationService educationService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EducationDtoPart createEntity(@RequestBody EducationDtoPart educationDtoPart){
        return educationService.createEntity(educationDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public EducationDtoPart updateEntity(@PathVariable Long id, @RequestBody EducationDtoPart educationDtoPart){
        return educationService.updateEntity(id, educationDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EducationDtoPart findById(@PathVariable Long id){
        return educationService.findById(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EducationDtoPart> listEntity(){
        return educationService.listDtoEducations();
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public MessageGeneral deleteEntityById(@PathVariable Long id, HttpServletRequest request){
        return educationService.deleteEntityById(id, request);
    }
}
