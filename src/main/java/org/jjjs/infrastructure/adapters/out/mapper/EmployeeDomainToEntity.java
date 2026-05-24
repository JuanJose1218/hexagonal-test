package org.jjjs.infrastructure.adapters.out.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.jjjs.domain.model.Employee;
import org.jjjs.infrastructure.adapters.out.EmployeeEntity;

import java.util.function.Function;

@ApplicationScoped
public class EmployeeDomainToEntity implements Function<Employee, EmployeeEntity> {
    @Override
    public EmployeeEntity apply(Employee employeeDomain) {
        return new EmployeeEntity(
                employeeDomain.getId(),
                employeeDomain.getFirstName(),
                employeeDomain.getSecondName(),
                employeeDomain.getLastName(),
                employeeDomain.getSecondLastName(),
                employeeDomain.getAge(),
                employeeDomain.getGender(),
                employeeDomain.getBirthDate(),
                employeeDomain.getJobPosition(),
                employeeDomain.getCreatedAt(),
                employeeDomain.getStatus()
        );
    }
}
