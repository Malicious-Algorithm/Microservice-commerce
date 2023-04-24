package com.microservice.products.service;

import com.microservice.products.dto.ResponseAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;


@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseAuthorization authorization(String authorizationReq)throws Exception{
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationReq);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseAuthorization auth = restTemplate.exchange("http://localhost:9099/getAuthentcated", HttpMethod.GET, request, ResponseAuthorization.class).getBody();
            return auth;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
