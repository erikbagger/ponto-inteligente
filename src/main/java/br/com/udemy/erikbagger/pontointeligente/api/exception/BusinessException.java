package br.com.udemy.erikbagger.pontointeligente.api.exception;

public class BusinessException extends BadRequestException {

    public BusinessException(String code, String message) {
        super(code, message);
    }
}
