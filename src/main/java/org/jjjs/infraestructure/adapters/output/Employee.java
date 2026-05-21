package org.jjjs.infraestructure.adapters.output;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "employees")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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





}
