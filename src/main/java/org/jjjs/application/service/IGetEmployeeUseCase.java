package org.jjjs.application.service;

import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;

public interface IGetEmployeeUseCase {

    void getAllEmployees();

    void getEmployeeById(Long idEmployee);

    void getEmployeeByName(String name);
}
