package br.com.udemy.erikbagger.pontointeligente.api.domain.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Errors {

	private Collection<ObjectError> errorList;

	public Collection<ObjectError> getErrorList() {
		return errorList;
	}

	public void addError(ObjectError... error) {
		if(this.errorList == null) {
			this.errorList = new ArrayList<>();
		}
		this.errorList.addAll(Arrays.asList(error));
	}
}
