package com.jens.aiplayground.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnvService {

    @Value("${test}")
    private String test;

    @Value("${dalle}")
    private String dalle;


    public String getTest(){
        return test;
    }

    public String getKey(){
        return dalle;
    }


}

