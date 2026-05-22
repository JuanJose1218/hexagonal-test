package org.jjjs.infrastructure.adapters.output;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "employees")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_last_name")
    private String secondLastName;

    private Integer age;

    private String gender;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "job_position")
    private String jobPosition;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private Boolean status;

    public EmployeeEntity(String firstName, String secondName, String lastName, String secondLastName, Integer age, String gender, LocalDate birthDate, String jobPosition, LocalDateTime createdAt, Boolean status) {
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
}
