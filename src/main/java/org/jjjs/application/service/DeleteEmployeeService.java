package org.jjjs.application.service;

import org.jjjs.application.port.in.DeleteEmployeesUseCase;
import org.jjjs.application.port.out.EmployeeRepository;

public class DeleteEmployeeService implements DeleteEmployeesUseCase {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.getById(id);
        employeeRepository.deleteById(id);

    }
}
