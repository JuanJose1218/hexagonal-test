package org.jjjs.application.service;

import org.jjjs.application.port.in.GetEmployeesResponse;
import org.jjjs.application.port.in.GetEmployeesUseCase;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.domain.exceptions.EntityNotFoundException;

import java.util.List;


public class GetEmployeesService implements GetEmployeesUseCase {

    private final EmployeeRepository employeeRepository;

    public GetEmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<GetEmployeesResponse> getAllEmployees() {
        return employeeRepository.getAll()
                .stream()
                .map(GetEmployeesResponse::new)
                .toList();

    }

    @Override
    public GetEmployeesResponse getEmployeeById(Long id) {
        var employee = employeeRepository.getById(id).orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));

        return new GetEmployeesResponse(employee);
    }

    @Override
    public List<GetEmployeesResponse> getEmployeesByName(String name) {
        return employeeRepository.getByName(name)
                .stream()
                .map(GetEmployeesResponse::new)
                .toList();
    }
}
