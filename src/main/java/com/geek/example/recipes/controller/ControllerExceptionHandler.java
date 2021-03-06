package com.geek.example.recipes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception ex) {
        log.error("Handling Number Format Exception");
        log.error(ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("400error");
        mav.addObject("ex", ex);
        return mav;
    }
}
