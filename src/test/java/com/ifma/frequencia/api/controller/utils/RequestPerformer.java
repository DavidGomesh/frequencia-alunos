package com.ifma.frequencia.api.controller.utils;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequestPerformer {
    
    private final TestRestTemplate testRestTemplate;

    public ResponseEntity<?> post(String route, HttpEntity<?> httpEntity, Class<?> clazz){
        return testRestTemplate.exchange(
            route, HttpMethod.POST, httpEntity, clazz
        );
    }

}
