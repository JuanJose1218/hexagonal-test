package org.jjjs.application.service;

import org.jjjs.domain.port.EmployeeRepository;

public class GetEmployeesUseCase implements IGetEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public GetEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void getAllEmployees() {

    }

    @Override
    public void getEmployeeById(Long idEmployee) {

    }

    @Override
    public void getEmployeeByName(String name) {

    }
}
