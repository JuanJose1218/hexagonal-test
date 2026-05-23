package org.jjjs.application.service;

import org.jjjs.application.port.command.UpdateEmployeeCommand;
import org.jjjs.application.port.in.UpdateEmployeeUseCase;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.domain.model.Employee;

public class UpdateEmployeeService implements UpdateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void updateById(Long id, UpdateEmployeeCommand updateEmployeeRequest) {
        var employee = employeeRepository.getById(id);
        employee.updateInformation(
                id,
                updateEmployeeRequest.firstName(),
                updateEmployeeRequest.secondName(),
                updateEmployeeRequest.lastName(),
                updateEmployeeRequest.secondLastName(),
                updateEmployeeRequest.gender(),
                updateEmployeeRequest.birthDate(),
                updateEmployeeRequest.jobPosition(),
                updateEmployeeRequest.isActive()
        );
        employeeRepository.updateById(employee);


    }
}
