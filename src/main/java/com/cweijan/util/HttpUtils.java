package com.cweijan.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class HttpUtils{

    public static <T> T getFor(String url, Class<T> clazz){

        return new RestTemplate().getForObject(url, clazz);
    }

    public static String get(String url){

        return getFor(url, String.class);
    }

    public static <T> T postFor(String url, Object params, Class<T> clazz){

        return new RestTemplate().postForObject(url, params, clazz);
    }

    public static <T> T postJsonFor(String url, String json, Class<T> clazz){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type","application/json;charset=utf-8");

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        return new RestTemplate().postForObject(url,entity,clazz);
    }

    public static String post(String url, Object params){

        return postFor(url, params, String.class);
    }
}
