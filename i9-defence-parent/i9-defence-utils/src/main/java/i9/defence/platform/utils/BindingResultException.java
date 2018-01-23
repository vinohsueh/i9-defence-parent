package i9.defence.platform.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
    public String getErrorMessage() {
        //获取验证失败的第一个错误
        Set<String> keys = errors.keySet();
        List<String> keyList = new ArrayList<String> ();  
        keyList.addAll(keys);  
        return errors.get(keyList.get(0));
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
