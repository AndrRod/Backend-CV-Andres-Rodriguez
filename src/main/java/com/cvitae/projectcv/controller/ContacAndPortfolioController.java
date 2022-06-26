package com.cvitae.projectcv.controller;

import com.cvitae.projectcv.dto.ContactAndPorfolioDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.service.ContService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:3000"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@RestController
@RequestMapping("/contact")
public class ContacAndPortfolioController {
    @Autowired
    private ContService contService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContactAndPorfolioDtoPart createEntity(@RequestBody ContactAndPorfolioDtoPart contactAndPorfolioDtoPart){
        return contService.createEntity(contactAndPorfolioDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ContactAndPorfolioDtoPart updateEntity(@PathVariable Long id, @RequestBody ContactAndPorfolioDtoPart contactAndPorfolioDtoPart){
        return contService.updateEntity(id, contactAndPorfolioDtoPart);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ContactAndPorfolioDtoPart getById(@PathVariable Long id){
        return contService.findDtoById(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ContactAndPorfolioDtoPart> listDto(){
        return contService.findAll();
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public MessageGeneral deleteById(@PathVariable Long id, HttpServletRequest request){
        return contService.deleteById(id, request);
    }
}
