package com.example.hrm_game.data;

import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class URLConst {
    public static String HOST;

    public static String API;

    private Environment env;

//    public static StringBuilder URI;

    @PostConstruct
    public void initConstant(){
        HOST = env.getProperty("property.host");
        API = env.getProperty("property.api");
//        URI = new StringBuilder().append(HOST).append(API);
    }
}
