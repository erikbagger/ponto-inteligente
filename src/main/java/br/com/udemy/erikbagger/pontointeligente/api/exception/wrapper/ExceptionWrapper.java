package br.com.udemy.erikbagger.pontointeligente.api.exception.wrapper;

import br.com.udemy.erikbagger.pontointeligente.api.exception.AbstractException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.BusinessException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.NotFoundException;
import br.com.udemy.erikbagger.pontointeligente.api.exception.response.ErrorDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
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

    public ExceptionWrapper(NotFoundException ex){ this.error = new ErrorDetails(ex.getCode(), ex.getMessage()); }

    public ErrorDetails getError() {
        return error;
    }
}
