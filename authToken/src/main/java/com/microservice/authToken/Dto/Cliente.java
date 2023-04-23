package com.microservice.authToken.Dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "cliente")
@AllArgsConstructor
@RequiredArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido;
    @NonNull
    //quizas buscar en el codigo del Starkoff como era el validador o la regex para los mails
    private String email;
    @NonNull
    private String password;

    public Cliente(){}
}