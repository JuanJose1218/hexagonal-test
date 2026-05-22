package org.jjjs.application.service;

import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.application.port.in.CreateEmployeeUseCase;
import org.jjjs.application.port.in.CreateEmployeeResponse;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.domain.exceptions.BusinessRuleException;
import org.jjjs.domain.model.Employee;

import java.util.List;

public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final Integer MINIMUM_AGE = 18;

    public CreateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CreateEmployeeResponse create(List<CreateEmployeeCommand> employeeCommands) {
        boolean tieneMenoresDeEdad = employeeCommands.stream()
                .anyMatch(emp -> emp.age() < MINIMUM_AGE);
        if (tieneMenoresDeEdad) {
            throw new BusinessRuleException("El lote contiene empleados menores de edad. Operación cancelada.");
        }

        var domainEmployees = employeeCommands.stream()
                .map(employeeCommand ->
                        new Employee(
                                employeeCommand.firstName(),
                                employeeCommand.secondName(),
                                employeeCommand.lastName(),
                                employeeCommand.secondLastName(),
                                employeeCommand.age(),
                                employeeCommand.gender(),
                                employeeCommand.birthDate(),
                                employeeCommand.jobPosition()
                        )).toList();
        employeeRepository.saveAll(domainEmployees);


        return new CreateEmployeeResponse("guradado exitoso");
    }
}
