package org.jjjs.infraestructure.adapters.input.rest.controller;


import jakarta.validation.Valid;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.jjjs.application.service.CreateEmployeeService;

import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;


@RequiredArgsConstructor
@Path("/employees")
public class EmployeeController {

    private final CreateEmployeeService createEmployeeService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public void saveEmployee(@Valid EmployeeRequest employeeRequest) {
        createEmployeeService.save(employeeRequest);

    }


}
