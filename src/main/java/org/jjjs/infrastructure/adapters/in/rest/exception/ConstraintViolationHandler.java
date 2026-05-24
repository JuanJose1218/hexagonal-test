package org.jjjs.infrastructure.adapters.in.rest.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var fieldErrors = exception.getConstraintViolations().stream()
                .map(violation -> {
                    var propertyPath = violation.getPropertyPath().toString();
                    var fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
                    return new ValidationErrorResponse.FieldError(fieldName, violation.getMessage());
                })
                .toList();
        var errorBody = new ValidationErrorResponse(
                "La validación de los datos de entrada falló.",
                fieldErrors
        );
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorBody)
                .build();
    }
}
