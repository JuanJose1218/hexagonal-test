package org.jjjs.application.port.command;


import java.time.LocalDate;

public record CreateEmployeeCommand(String firstName, String secondName, String lastName, String secondLastName, Integer age,
                                    String gender, LocalDate birthDate, String jobPosition) {
}
