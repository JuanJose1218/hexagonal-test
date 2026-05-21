package org.jjjs.application.service;

import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;

public interface CreateEmployeeService {

    void save(EmployeeRequest productRequest);
}
