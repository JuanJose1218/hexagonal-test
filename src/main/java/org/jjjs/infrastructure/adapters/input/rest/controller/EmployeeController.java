package org.jjjs.infrastructure.adapters.input.rest.controller;


import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.jjjs.application.port.in.CreateEmployeeUseCase;
import org.jjjs.application.port.in.DeleteEmployeesUseCase;
import org.jjjs.application.port.in.GetEmployeesUseCase;
import org.jjjs.infrastructure.adapters.input.rest.dto.EmployeeRequest;
import org.jjjs.infrastructure.adapters.input.rest.mappers.EmployeeRequestToCommand;

import java.util.List;


@RequiredArgsConstructor
@Path("/employees")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final GetEmployeesUseCase getEmployeesUseCase;
    private final EmployeeRequestToCommand employeeRequestToCommand;
    private final DeleteEmployeesUseCase deleteEmployeesUseCase;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmployee(@Valid List<EmployeeRequest> employeeRequest) {
        var employeeCommand = employeeRequestToCommand.apply(employeeRequest);
        return Response.status(Response.Status.CREATED)
                .entity(createEmployeeUseCase.create(employeeCommand))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        return Response.ok()
                .entity(getEmployeesUseCase.getAllEmployees())
                .build();


    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Long id) {
        return Response.ok()
                .entity(getEmployeesUseCase.getEmployeeById(id))
                .build();

    }


    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") Long id) {
        deleteEmployeesUseCase.deleteById(id);
        return Response.ok()
                .build();
    }

}
