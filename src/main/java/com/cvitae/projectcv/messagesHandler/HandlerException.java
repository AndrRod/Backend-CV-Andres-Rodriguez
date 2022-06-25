package com.cvitae.projectcv.messagesHandler;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler({NotFoundException.class})
    public MessageGeneral notfoundException(NotFoundException exception, HttpServletRequest request){
        return new MessageGeneral(exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler({BadRequestException.class})
    public MessageGeneral badRequestException(NotFoundException exception, HttpServletRequest request){
        return new MessageGeneral(exception.getMessage(), request.getRequestURI());
    }
}
