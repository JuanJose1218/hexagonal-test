package org.jjjs.infraestructure.adapters.input.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.jjjs.domain.exceptions.BusinessRuleException;


import java.util.Map;


@Provider
@Slf4j
public class ExceptionHandlerDemo implements ExceptionMapper<BusinessRuleException> {

    @Override
    public Response toResponse(BusinessRuleException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje", exception.getMessage())).build();
    }
}
