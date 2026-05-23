package org.jjjs.infrastructure.adapters.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de datos requerido para registrar un nuevo empleado")
public class CreateEmployeeRequest {
    @Schema(description = "Primer nombre del empleado", example = "Juan", required = true)
    @NotBlank(message = "name was not empty")
    private String firstName;
    private String secondName;
    @Schema(description = "Primer apeliido del empleado", example = "Perez", required = true)
    @NotBlank(message = "lastName was not empty")
    private String lastName;
    private String secondLastName;
    @Schema(description = "Genero del empleado", example = "M", required = true)
    @NotBlank(message = "gender was not empty")
    private String gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "The birth date cannot be null")
    private LocalDate birthDate;
    @NotBlank(message = "jobPosition was not empty")
    @Schema(description = "Puesto del empleado", example = "Dev", required = true)
    private String jobPosition;
}
