package org.jjjs.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jjjs.application.port.command.UpdateEmployeeCommand;
import org.jjjs.domain.exceptions.BusinessRuleException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Getter

@NoArgsConstructor
@EqualsAndHashCode
public class Employee {
    private static final int MINIMUM_AGE = 18;
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

    /*Constructor para craacion de empleado
     * */
    public Employee(String firstName,
                    String secondName,
                    String lastName,
                    String secondLastName,
                    String gender,
                    LocalDate birthDate,
                    String jobPosition) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.jobPosition = jobPosition;
        this.createdAt = LocalDateTime.now();
        this.status = Boolean.TRUE;
        var age = calculateAge(birthDate);
        if (age < MINIMUM_AGE) {
            throw new BusinessRuleException("The batch contains underage employees.");
        }
        this.age = age;

    }

    /*Constructor para convertir de entity a domain
     * */
    public Employee(Long id,
                    String firstName,
                    String secondName,
                    String lastName,
                    String secondLastName,
                    Integer age,
                    String gender,
                    LocalDate birthDate,
                    String jobPosition,
                    LocalDateTime createdAt,
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


    public void updateInformation(Long id,
                    String firstName,
                    String secondName,
                    String lastName,
                    String secondLastName,
                    String gender,
                    LocalDate birthDate,
                    String jobPosition,
                    Boolean isActive) {
        validateAge(birthDate);
        this.id = id;
        if (StringUtils.isNotBlank(firstName)) {
            this.firstName = firstName;
        }
        if (StringUtils.isNotBlank(secondName)) {
            this.secondName = secondName;
        }
        if (StringUtils.isNotBlank(lastName)) {
            this.lastName = lastName;
        }
        if (StringUtils.isNotBlank(secondLastName)) {
            this.secondLastName = secondLastName;
        }
        if (StringUtils.isNotBlank(gender)) {
            this.gender = gender;
        }

        if (StringUtils.isNotBlank(jobPosition)) {
            this.jobPosition = jobPosition;
        }
        if (isActive != null) {
            this.status = isActive;
        }

    }


    public static int calculateAge(LocalDate birthDate) {
        var today = LocalDate.now();
        var period = Period.between(birthDate, today);
        return period.getYears();
    }

    public void validateAge(LocalDate birthDate) {

        if (birthDate != null) {
            var age = calculateAge(birthDate);
            if (age < MINIMUM_AGE) {
                throw new BusinessRuleException("The employee age is invalid.");
            } else {
                this.age = age;
            }
        }
    }

}
