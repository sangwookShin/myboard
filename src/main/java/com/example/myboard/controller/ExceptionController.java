package com.example.myboard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.example.myboard.controller")
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public String notFound(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "errors/404-error";
    }
}
