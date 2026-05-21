package org.jjjs.application.service;

import org.jjjs.domain.port.EmployeeRepository;
import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;

public class UpdateEmployeeUseCase implements IUpdateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void update(EmployeeRequest productRequest) {

    }
}
