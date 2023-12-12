package com.platform.integration.model.response.failure;

import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

@NoArgsConstructor
public class IdNotFoundException extends NoSuchElementException {
    public IdNotFoundException(String message) {
        super(message);
    }
}