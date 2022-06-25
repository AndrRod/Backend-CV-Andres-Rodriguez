package com.cvitae.projectcv.service;
import com.cvitae.projectcv.dto.PersonDtoCreate;
import com.cvitae.projectcv.dto.UserTokenResponseDto;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PersonAuthService {
//    Authentication authenticationFilter(String email, String password) throws AuthenticationException;
    UserTokenResponseDto userLogin(String email, String password, HttpServletRequest request);
    void refreshToken(UserTokenResponseDto responseDto, HttpServletRequest request, HttpServletResponse response) throws IOException;
    public MessageGeneral createUser(PersonDtoCreate personDtoCreate, HttpServletRequest request);
}
