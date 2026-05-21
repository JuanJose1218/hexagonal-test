package org.jjjs.application.service;

import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;

public interface IUpdateEmployeeUseCase {

    void update(EmployeeRequest productRequest);
}
