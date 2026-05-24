package org.jjjs.infrastructure.adapters.in.rest.controller;


import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jjjs.application.port.in.CreateEmployeeUseCase;
import org.jjjs.application.port.in.DeleteEmployeesUseCase;
import org.jjjs.application.port.in.GetEmployeesUseCase;
import org.jjjs.application.port.in.UpdateEmployeeUseCase;
import org.jjjs.infrastructure.adapters.in.rest.dto.CreateEmployeeRequest;
import org.jjjs.infrastructure.adapters.in.rest.dto.UpdateEmployeeRequest;
import org.jjjs.infrastructure.adapters.in.rest.mappers.CreateEmployeeRequestToCommand;
import org.jjjs.infrastructure.adapters.in.rest.mappers.UpdateEmployeeRequestToCommand;

import java.util.List;


@RequiredArgsConstructor
@Path("/employees")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final GetEmployeesUseCase getEmployeesUseCase;
    private final DeleteEmployeesUseCase deleteEmployeesUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final CreateEmployeeRequestToCommand employeeRequestToCommand;
    private final UpdateEmployeeRequestToCommand updateEmployeeRequestToCommand;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Registrar empleados", description = "Registra empleados en la base de datos")
    @APIResponse(responseCode = "201", description = "Empleado registrado con éxito")
    public Response saveEmployee(@Valid List<CreateEmployeeRequest> employeeRequest) {
        var employeeCommand = employeeRequestToCommand.apply(employeeRequest);
        return Response.status(Response.Status.CREATED)
                .entity(createEmployeeUseCase.create(employeeCommand))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener todos los empleados ", description = "Busca todos los empleados en el sistema ")
    @APIResponse(responseCode = "200", description = "Empleado encontrado con éxito")
    public Response getEmployees() {
        return Response.ok()
                .entity(getEmployeesUseCase.getAllEmployees())
                .build();


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    @Operation(summary = "Obtener empleado por nombre", description = "Busca un empleado en el sistema usando su nombre.")
    @APIResponse(responseCode = "200", description = "Empleado encontrado con éxito")
    public Response getEmployees(
            @Parameter(
                    description = "El nombre del empleado en la base de datos",
                    required = true,
                    example = "Juan"
            )
            @QueryParam("name") String name) {
        return Response.ok()
                .entity(getEmployeesUseCase.getEmployeesByName(name))
                .build();


    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener empleado por ID", description = "Busca un empleado en el sistema usando su identificador único.")
    @APIResponse(responseCode = "200", description = "Empleado encontrado con éxito")
    @APIResponse(responseCode = "404", description = "El ID proporcionado no pertenece a ningún empleado")
    public Response getEmployeeById(
            @Parameter(
                    description = "El identificador único del empleado en la base de datos",
                    required = true,
                    example = "1045"
            )
            @PathParam("id") Long id) {
        return Response.ok()
                .entity(getEmployeesUseCase.getEmployeeById(id))
                .build();

    }


    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Eliminar empleado por ID", description = "Elimina un empleado en el sistema usando su identificador único.")
    @APIResponse(responseCode = "204", description = "Empleado eliminado con éxito")
    @APIResponse(responseCode = "404", description = "El ID proporcionado no pertenece a ningún empleado")
    public Response deleteEmployee(
            @Parameter(
                    description = "El identificador único del empleado en la base de datos",
                    required = true,
                    example = "1045"
            )
            @PathParam("id") Long id) {
        deleteEmployeesUseCase.deleteById(id);
        return Response.noContent()
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Operation(summary = "Actualiza empleado por ID", description = "Actualiza datos de  un empleado en el sistema usando su identificador único.")
    @APIResponse(responseCode = "204", description = "Empleado actualizado con éxito")
    @APIResponse(responseCode = "404", description = "El ID proporcionado no pertenece a ningún empleado")
    public Response updateEmployee(@PathParam("id") Long id, UpdateEmployeeRequest updateEmployeeRequest) {
        var updateEmployeeCommand = updateEmployeeRequestToCommand.apply(updateEmployeeRequest);
        updateEmployeeUseCase.updateById(id, updateEmployeeCommand);
        return Response.noContent()
                .build();
    }

}
