package org.jjjs.application.service;

import org.jjjs.application.mapper.EmployeeCommandToDomain;
import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.application.port.in.CreateEmployeeResponse;
import org.jjjs.application.port.in.CreateEmployeeUseCase;
import org.jjjs.application.port.out.EmployeeRepository;

import java.util.List;

public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public CreateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CreateEmployeeResponse create(List<CreateEmployeeCommand> employeeCommands) {

        var domainEmployees = employeeCommands.stream()
                .map(EmployeeCommandToDomain::toDomain
                ).toList();
        employeeRepository.saveAll(domainEmployees);


        return new CreateEmployeeResponse("guradado exitoso");
    }
}
