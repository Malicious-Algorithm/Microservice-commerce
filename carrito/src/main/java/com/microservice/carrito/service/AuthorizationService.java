package com.microservice.carrito.service;

import com.microservice.carrito.payload.response.ResponseAuthorization;

public interface AuthorizationService {
    public ResponseAuthorization authorization(String authorizationReq)throws Exception;

}
