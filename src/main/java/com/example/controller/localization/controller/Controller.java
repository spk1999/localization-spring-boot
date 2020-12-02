package com.example.controller.localization.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class Controller {

    @GetMapping
    public String getHello(
            @RequestParam( value ="lang",required = false ) String lang,
                           @RequestHeader (value ="Accept-Language", required = false) String acceptLanguage)
    {

        System.out.println("Value of lang is "+lang );
        System.out.println("Value of accept language is "+acceptLanguage );

        return "hello";
    }

}
