package i9.defence.platform.utils;

import java.util.HashMap;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingResultException extends RuntimeException {
    
    private static final long serialVersionUID = -8974911471569919770L;
    
    public HashMap<String, String> errors = new HashMap<String, String>();
    
    public HashMap<String, String> toErrors() {
        return errors;
    }
    
    public void addError(String error, String errorMessage) {
        this.errors.put(error, errorMessage);
    }
    
    public BindingResultException(List<FieldError> fieldErrors) {
        for (FieldError fieldError : fieldErrors) {
            System.out.println("dto : " + fieldError.getObjectName() + ", fieldName : " + fieldError.getField()
                    + ", value : " + fieldError.getRejectedValue() + ", err : " + fieldError.getDefaultMessage());
            String error = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            this.addError(error, errorMessage);
        }
    }

    public BindingResultException(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            System.out.println("dto : " + fieldError.getObjectName() + ", fieldName : " + fieldError.getField()
                    + ", value : " + fieldError.getRejectedValue() + ", err : " + fieldError.getDefaultMessage());
            String error = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            this.addError(error, errorMessage);
        }
    }

    public BindingResultException() {
    }
}
