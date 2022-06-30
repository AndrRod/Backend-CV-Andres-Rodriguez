package com.cvitae.projectcv.controller;
import com.cvitae.projectcv.dto.PersonDtoCreate;
import com.cvitae.projectcv.dto.UserTokenResponseDto;
import com.cvitae.projectcv.dto.mapper.PersonMapper;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.service.PersonAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = {"http://127.0.0.1:3000", "https://cv-andres-rodriguez.netlify.app/"}
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class PersonAuthController {
    @Autowired
    private PersonAuthService personAuthService;
    @Autowired
    private PersonMapper personMapper;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public MessageGeneral registerPerson(@RequestBody PersonDtoCreate personDtoCreate, HttpServletRequest request){
        return personAuthService.registerUser(personDtoCreate, request);
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserTokenResponseDto loginUser(@RequestBody PersonDtoCreate user, HttpServletRequest request){
        return personAuthService.userLogin(user.getEmail(), user.getPassword(), request);
    }
    @GetMapping("/logoutsuccess")
    @ResponseStatus(HttpStatus.OK)
    public MessageGeneral logout (HttpServletRequest request){
        return new MessageGeneral("user was logout", request.getRequestURI());
    }
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refreshToken(@RequestBody UserTokenResponseDto form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        personAuthService.refreshToken(form, request, response);
    }
}