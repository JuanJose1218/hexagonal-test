package org.jjjs.infraestructure.adapters.config;


import jakarta.enterprise.context.ApplicationScoped;

import jakarta.enterprise.inject.Produces;

import org.jjjs.application.service.CreateEmployeeService;
import org.jjjs.application.service.CreateEmployeeUseCase;
import org.jjjs.domain.port.EmployeeRepository;

@ApplicationScoped
public class QuarkusAppConfig {

    @Produces
    public CreateEmployeeService createEmployeeUseCase(EmployeeRepository productRepository) {
        return new CreateEmployeeUseCase(productRepository);

    }
}
