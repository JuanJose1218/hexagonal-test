package org.jjjs.infrastructure.adapters.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeRequest {
    @NotBlank(message = "name ")
    private String firstName;
    private String secondName;
    @NotBlank(message = "lastName")
    private String lastName;
    private String secondLastName;
    private Integer age;
    @NotBlank(message = "gender")
    private String gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @NotBlank(message = "jobPosition")
    private String jobPosition;
}
