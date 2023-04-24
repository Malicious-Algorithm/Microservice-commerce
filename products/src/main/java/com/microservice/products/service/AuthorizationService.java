package com.microservice.products.service;

import com.microservice.products.dto.ResponseAuthorization;

public interface AuthorizationService {
    public ResponseAuthorization authorization(String authorizationReq)throws Exception;
}
