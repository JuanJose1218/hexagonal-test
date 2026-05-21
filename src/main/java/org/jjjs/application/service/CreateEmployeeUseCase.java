package org.jjjs.application.service;

import org.jjjs.domain.port.EmployeeRepository;
import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;

public class CreateEmployeeUseCase implements CreateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public CreateEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void save(EmployeeRequest productRequest) {

    }
}
