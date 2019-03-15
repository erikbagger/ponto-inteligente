package br.com.udemy.erikbagger.pontointeligente.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<String> getMessages() {
		return this.errorList.stream().map(ObjectError::getMessage).collect(Collectors.toList());
	}
}
