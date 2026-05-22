package org.jjjs.infrastructure.adapters.input.rest.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.infrastructure.adapters.input.rest.dto.EmployeeRequest;

import java.util.List;
import java.util.function.Function;

@ApplicationScoped
public class EmployeeRequestToCommand implements Function<List<EmployeeRequest>, List<CreateEmployeeCommand>> {
    @Override
    public List<CreateEmployeeCommand> apply(List<EmployeeRequest> employeeRequests) {
        return employeeRequests.stream().map(employeeRequest ->
                new CreateEmployeeCommand(employeeRequest.getFirstName(),
                        employeeRequest.getSecondName(),
                        employeeRequest.getLastName(),
                        employeeRequest.getSecondLastName(),
                        employeeRequest.getAge(),
                        employeeRequest.getGender(),
                        employeeRequest.getBirthDate(),
                        employeeRequest.getJobPosition())
        ).toList();

    }
}
