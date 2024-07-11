package com.mountana.api.utility;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FormValidationUtil {
    public String buildErrorMessage(Errors errors){
        return errors.getAllErrors()
                .stream()
                .map(x -> ((FieldError) x).getField() + " " + x.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

    public Map<String, String> errorMessageMapper(Errors errors){
        Map<String, String> mappedErrors = new HashMap<>();
        errors.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mappedErrors.put(fieldName, errorMessage);
        });
        return mappedErrors;
    }
}
