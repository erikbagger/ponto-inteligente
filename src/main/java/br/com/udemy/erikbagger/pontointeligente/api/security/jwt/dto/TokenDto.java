package br.com.udemy.erikbagger.pontointeligente.api.security.jwt.dto;

public class TokenDto {

    private String token;

    public TokenDto() {
    }

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}