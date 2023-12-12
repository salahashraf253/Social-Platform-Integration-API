package com.platform.integration.model.response.failure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class BadRequestResponse {

    private String message;


    private Map<String, List<String>> failed_validation_attributes;
}