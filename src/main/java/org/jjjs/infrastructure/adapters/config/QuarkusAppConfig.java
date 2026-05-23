package org.jjjs.infrastructure.adapters.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.jjjs.application.port.in.CreateEmployeeUseCase;
import org.jjjs.application.port.in.DeleteEmployeesUseCase;
import org.jjjs.application.port.in.GetEmployeesUseCase;
import org.jjjs.application.port.in.UpdateEmployeeUseCase;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.application.service.CreateEmployeeService;
import org.jjjs.application.service.DeleteEmployeeService;
import org.jjjs.application.service.GetEmployeesService;
import org.jjjs.application.service.UpdateEmployeeService;

@ApplicationScoped
public class QuarkusAppConfig {

    @Produces
    @ApplicationScoped
    public CreateEmployeeUseCase createEmployeeUseCase(EmployeeRepository employeeRepository) {
        return new CreateEmployeeService(employeeRepository);

    }

    @Produces
    @ApplicationScoped
    public GetEmployeesUseCase getEmployeesUseCase(EmployeeRepository employeeRepository) {
        return new GetEmployeesService(employeeRepository);

    }

    @Produces
    @ApplicationScoped
    public DeleteEmployeesUseCase deleteEmployeesUseCase(EmployeeRepository employeeRepository) {
        return new DeleteEmployeeService(employeeRepository);

    }

    @Produces
    @ApplicationScoped
    public UpdateEmployeeUseCase updateEmployeeUseCase(EmployeeRepository employeeRepository) {
        return new UpdateEmployeeService(employeeRepository);

    }
}
