package org.jjjs.application.port.in;

import org.jjjs.domain.model.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record GetEmployeesResponse(
        Long id,
        String firstName,
        String secondName,
        String lastName,
        String secondLastName,
        Integer age, String gender,
        LocalDate birthDate,
        String jobPosition,
        LocalDateTime createdAt,
        Boolean status) {

    public GetEmployeesResponse(Employee employee) {
        this(
                employee.getId(),
                employee.getFirstName(),
                employee.getSecondName(),
                employee.getLastName(),
                employee.getSecondLastName(),
                employee.getAge(),
                employee.getGender(),
                employee.getBirthDate(),
                employee.getJobPosition(),
                employee.getCreatedAt(),
                employee.getStatus()
        );
    }

}
