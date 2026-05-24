package org.jjjs.infrastructure.adapters.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de datos requerido para actualizar un empleado existente")
public class UpdateEmployeeRequest {
    @Schema(description = "Primer nombre del empleado", example = "Juan")
    private String firstName;
    @Schema(description = "Segundo nombre del empleado", example = "Luis")
    private String secondName;
    @Schema(description = "Primer apeliido del empleado", example = "Perez")
    private String lastName;
    @Schema(description = "Segundo apellido del empleado", example = "Lopez")
    private String secondLastName;
    @Schema(description = "Genero del empleado", example = "M")
    private String gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Fecha de nacimiento del empleado", example = "20-10-2026")
    private LocalDate birthDate;
    @Schema(description = "Puesto del empleado", example = "Dev")
    private String jobPosition;
    @Schema(description = "Estatus del empleado", example = "true")
    private Boolean isActive;
}
