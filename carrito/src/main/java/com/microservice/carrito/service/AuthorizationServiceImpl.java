package com.microservice.carrito.service;

import com.microservice.carrito.payload.response.ResponseAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            return restTemplate.exchange("http://authtoken:9099/getAuthentcated", HttpMethod.GET, request, ResponseAuthorization.class).getBody();
            //aca throw Exception UNAUTHORIZED ya que sin esto devuelve un 500!
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
