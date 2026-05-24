package org.jjjs.infrastructure.adapters.in.rest.controller;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class LoggingFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) {
        var method = requestContext.getMethod();
        var path = requestContext.getUriInfo().getPath();
        log.info("Receiving headers from {} {}", method, path);
        requestContext.getHeaders()
                .forEach((headerName, headerValues) ->
                        log.info("   {}: {}", headerName, String.join(", ", headerValues)));
    }
}
