package com.platform.integration.execption;


import com.platform.integration.model.response.failure.BadRequestResponse;
import com.platform.integration.model.response.failure.IdNotFoundException;
import com.platform.integration.model.response.failure.InternalServerResponse;
import com.platform.integration.model.response.failure.UnAuthorizedResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Set<String> failedAttributesNamesInValidation = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet());


        Map<String, List<String>> errorsAndCauses = new HashMap<>();

        failedAttributesNamesInValidation.forEach(error -> errorsAndCauses.put(error, new ArrayList<>()));


        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errorsAndCauses.get(fieldError.getField()).add(fieldError.getDefaultMessage()));


        return new ResponseEntity<>(BadRequestResponse.builder().message("Validation Failed").failed_validation_attributes(errorsAndCauses).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleGeneralException(Exception exception) {
        return new ResponseEntity<>(
                InternalServerResponse.builder().message(exception.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleAuthenticationException() {
        return new ResponseEntity<>(
                UnAuthorizedResponse.builder().message("Invalid user credentials").build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({IdNotFoundException.class})
    public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException exception) {
        return new ResponseEntity<>(
                InternalServerResponse.builder().message(exception.getMessage()).build(),
                HttpStatus.NOT_FOUND);
    }


}