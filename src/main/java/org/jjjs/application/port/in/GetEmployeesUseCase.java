package org.jjjs.application.port.in;

import java.util.List;

public interface GetEmployeesUseCase {
    List<GetEmployeesResponse> getAllEmployees();

    GetEmployeesResponse getEmployeeById(Long id);

    List<GetEmployeesResponse> getEmployeesByName(String name);
}
