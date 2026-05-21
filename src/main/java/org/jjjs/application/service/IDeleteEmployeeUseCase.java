package org.jjjs.application.service;

import org.jjjs.infraestructure.adapters.input.rest.dto.EmployeeRequest;

public interface IDeleteEmployeeUseCase {

    void delete(Long idEmployee);
}
