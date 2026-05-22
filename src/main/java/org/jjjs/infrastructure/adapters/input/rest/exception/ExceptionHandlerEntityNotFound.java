package org.jjjs.infrastructure.adapters.input.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.jjjs.domain.exceptions.BusinessRuleException;
import org.jjjs.domain.exceptions.EntityNotFoundException;
import org.jjjs.infrastructure.adapters.input.rest.dto.ErrorResponse;


import java.util.Map;


@Provider
@Slf4j
public class ExceptionHandlerEntityNotFound implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(exception.getMessage())).build();
    }
}
