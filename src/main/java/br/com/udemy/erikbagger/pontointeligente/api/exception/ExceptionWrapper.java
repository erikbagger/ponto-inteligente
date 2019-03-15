package br.com.udemy.erikbagger.pontointeligente.api.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class ExceptionWrapper {

    private ErrorDetails error;

    public ExceptionWrapper(AbstractException ex){
        this.error = new ErrorDetails(ex.getCode(), ex.getErrors());
    }

    public ExceptionWrapper(BadRequestException ex){
        this.error = new ErrorDetails(ex.getCode(), ex.getMessage());
    }

    public ExceptionWrapper(MethodArgumentNotValidException ex){
        this.error = new ErrorDetails("", ex.getBindingResult().getFieldErrors());
    }
}
