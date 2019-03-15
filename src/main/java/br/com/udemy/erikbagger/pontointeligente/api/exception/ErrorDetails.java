package br.com.udemy.erikbagger.pontointeligente.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

    private Integer index;
    private String code;
    private String name;
    private String message;
    private List<ErrorDetails> fields;

    public ErrorDetails(String code, List<FieldError> errors){
        this.code = code;
        this.message = String.format("Found %s invalid fields", errors.size());
        resolveNestedFieldErrors(errors);
    }

    public ErrorDetails(String code, String message){
        this.code = code;
        this.message = message;
    }

    private ErrorDetails(List<FieldError> children){
        String unresolvedParentName = children.get(0).getField();
        this.index = resolveIndexIfApplicable(unresolvedParentName);
        this.name = resolveParentName(unresolvedParentName);

        String objectName = children.get(0).getObjectName();
        children = children.stream()
                .map(c -> new FieldError(objectName, retrieveFieldName(c.getField()),
                        c.getRejectedValue(), c.isBindingFailure(),
                        c.getCodes(), c.getArguments(), c.getDefaultMessage()))
                .collect(Collectors.toList());
        resolveNestedFieldErrors(children);
    }

    private ErrorDetails(FieldError error){
        this.code = "invalid";
        this.message = error.getDefaultMessage();
    }

    private void resolveNestedFieldErrors(List<FieldError> errors){
        this.fields = new ArrayList<>();
        List<FieldError> copy = new ArrayList<>(errors);
        for(FieldError error : copy){
            if(!isFlat(error.getField())){
                String node = extractNode(error.getField());
                List<FieldError> children = errors.stream().
                        filter(e -> e.getField().contains(node))
                        .collect(Collectors.toList());
                if(!children.isEmpty()){
                    this.addChildren(children);
                }
            } else {
                this.fields.add(new ErrorDetails(error));
            }
        }
    }

    private boolean isFlat(String field){
       return !field.contains(".");
    }

    private void addChildren(List<FieldError> errors){
        this.fields.add(new ErrorDetails(errors));
    }

    private String extractNode(String field){
        return field.substring(0, field.indexOf("."));
    }

    private String resolveParentName(String field){
        String lastIndex = ".";
        if(field.contains("[")){
            lastIndex = "[";
        }
        return field.substring(0, field.indexOf(lastIndex));
    }

    private Integer resolveIndexIfApplicable(String field){
        if(!field.contains("[")){
            return null;
        }
        return Integer.parseInt(field.substring(field.indexOf("[") + 1, field.indexOf("]")));
    }

    private String retrieveFieldName(String field){
        try{
            return field.substring(field.indexOf(".") + 1);
        } catch (Exception e){
            return null;
        }
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorDetails> getFields() {
        return fields;
    }

    public void setFields(List<ErrorDetails> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "index=" + index +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", fields=" + fields +
                '}';
    }
}
