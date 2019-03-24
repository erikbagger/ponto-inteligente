package br.com.udemy.erikbagger.pontointeligente.api.exception.wrapper;

import br.com.udemy.erikbagger.pontointeligente.api.exception.AbstractException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.response.ErrorDetails;

public class ExceptionWrapper {

    private ErrorDetails error;

    public ExceptionWrapper(AbstractException ex){
        this.error = new ErrorDetails(ex.getCode(), ex.getErrors());
    }

    public ExceptionWrapper(BadRequestException ex){
        this.error = new ErrorDetails(ex.getCode(), ex.getErrors());
    }

    public ExceptionWrapper(BusinessException ex){
        this.error = new ErrorDetails(ex.getCode(), ex.getMessage());
    }

}
