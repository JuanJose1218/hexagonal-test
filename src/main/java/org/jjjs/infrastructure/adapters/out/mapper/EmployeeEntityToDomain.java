package org.jjjs.infrastructure.adapters.out.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.jjjs.domain.model.Employee;
import org.jjjs.infrastructure.adapters.out.EmployeeEntity;

import java.util.function.Function;

@ApplicationScoped
public class EmployeeEntityToDomain implements Function<EmployeeEntity, Employee> {
    @Override
    public Employee apply(EmployeeEntity employeeEntity) {
        return new Employee(
                employeeEntity.getId(),
                employeeEntity.getFirstName(),
                employeeEntity.getSecondName(),
                employeeEntity.getLastName(),
                employeeEntity.getSecondLastName(),
                employeeEntity.getAge(),
                employeeEntity.getGender(),
                employeeEntity.getBirthDate(),
                employeeEntity.getJobPosition(),
                employeeEntity.getCreatedAt(),
                employeeEntity.getStatus()
        );
    }
}
