package com.jens.aiplayground.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnvService {

    @Value("${test}")
    private String test;

    @Value("${dalle}")
    private String dalle;

    @Value("${gpt4}")
    private String gpt4;

    public void setTest(){

    }

    public String getGpt4(){ return gpt4; }
    public String getTest(){
        return test;
    }

    public String getKey(){
        return dalle;
    }


}

