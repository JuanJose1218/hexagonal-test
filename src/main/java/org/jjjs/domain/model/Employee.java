package org.jjjs.domain.model;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter

@NoArgsConstructor
@EqualsAndHashCode
public class Employee {


    private String firstName;
    private String secondName;
    private BigDecimal lastName;
    private String secondLastName;
    private Integer age;
    private String gender;

    private LocalDate birthDate;

    private String jobPosition;

    private LocalDateTime createdAt;
    private Boolean status;

    public Employee(String firstName, String secondName, BigDecimal lastName, String secondLastName, Integer age, String gender, LocalDate birthDate, String jobPosition, LocalDateTime createdAt, Boolean status) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.jobPosition = jobPosition;
        this.createdAt = LocalDateTime.now();
        this.status = status;
    }
}
