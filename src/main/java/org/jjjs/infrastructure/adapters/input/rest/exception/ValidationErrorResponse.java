package org.jjjs.infrastructure.adapters.input.rest.exception;

import java.util.List;

public record ValidationErrorResponse(
        String message,
        List<FieldError> errors
) {
    public record FieldError(String field, String message) {}
}