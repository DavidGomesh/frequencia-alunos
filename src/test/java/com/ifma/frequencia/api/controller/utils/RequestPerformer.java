package com.ifma.frequencia.api.controller.utils;

import java.util.Map;

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

    public ResponseEntity<?> post(String route, Object requestBody, Class<?> clazz){
        HttpEntity<?> httpEntity = new HttpEntity<>(requestBody);
        return testRestTemplate.exchange(
            route, HttpMethod.POST, httpEntity, clazz
        );
    }

    public ResponseEntity<?> post(String route, Class<?> clazz, Map<String, String> args){
        return testRestTemplate.postForEntity(
            route, null, clazz, args
        );
    }
}
