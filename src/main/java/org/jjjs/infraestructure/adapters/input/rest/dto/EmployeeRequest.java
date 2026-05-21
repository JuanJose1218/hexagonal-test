package org.jjjs.infraestructure.adapters.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeRequest {
    @NotBlank(message = "el nombre del producto no puede ser null")
    private String firstName;
    private String secondName;
    private BigDecimal lastName;
    private String secondLastName;
    private Integer age;
    private String gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    private String jobPosition;
}
