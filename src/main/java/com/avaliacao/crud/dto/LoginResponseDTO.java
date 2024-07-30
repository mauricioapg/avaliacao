package com.avaliacao.crud.dto;

public class LoginResponseDTO {

    private String jwttoken;

    public LoginResponseDTO(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
