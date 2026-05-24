package org.jjjs.infrastructure.adapters.in.rest.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.infrastructure.adapters.in.rest.dto.CreateEmployeeRequest;

import java.util.List;
import java.util.function.Function;

@ApplicationScoped
public class CreateEmployeeRequestToCommand implements Function<List<CreateEmployeeRequest>, List<CreateEmployeeCommand>> {
    @Override
    public List<CreateEmployeeCommand> apply(List<CreateEmployeeRequest> employeeRequests) {
        return employeeRequests.stream().map(employeeRequest ->
                new CreateEmployeeCommand(employeeRequest.getFirstName(),
                        employeeRequest.getSecondName(),
                        employeeRequest.getLastName(),
                        employeeRequest.getSecondLastName(),
                        employeeRequest.getGender(),
                        employeeRequest.getBirthDate(),
                        employeeRequest.getJobPosition())
        ).toList();

    }
}
