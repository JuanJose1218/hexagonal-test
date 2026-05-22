package org.jjjs.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jjjs.infrastructure.adapters.output.EmployeeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter

@NoArgsConstructor
@EqualsAndHashCode
public class Employee {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String gender;

    private LocalDate birthDate;

    private String jobPosition;

    private LocalDateTime createdAt;
    private Boolean status;

    public Employee(String firstName, String secondName, String lastName, String secondLastName, Integer age, String gender, LocalDate birthDate, String jobPosition) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.jobPosition = jobPosition;
        this.createdAt = LocalDateTime.now();
        this.status = Boolean.TRUE;
    }

    public Employee(Long id,
                    String firstName,
                    String secondName,
                    String lastName,
                    String secondLastName,
                    Integer age, String gender, LocalDate birthDate, String jobPosition, LocalDateTime createdAt,
                    Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.jobPosition = jobPosition;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Employee(EmployeeEntity employeeEntity) {

        this.id = employeeEntity.getId();
        this.firstName = employeeEntity.getFirstName();
        this.secondName = employeeEntity.getSecondName();
        this.lastName = employeeEntity.getLastName();
        this.secondLastName = employeeEntity.getSecondLastName();
        this.age = employeeEntity.getAge();
        this.gender = employeeEntity.getGender();
        this.birthDate = employeeEntity.getBirthDate();
        this.jobPosition = employeeEntity.getJobPosition();
        this.createdAt = employeeEntity.getCreatedAt();
        this.status = employeeEntity.getStatus();
    }
}
