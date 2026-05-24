package org.jjjs.application.port.command;


import java.time.LocalDate;

public record UpdateEmployeeCommand(String firstName,
                                    String secondName,
                                    String lastName,
                                    String secondLastName,
                                    String gender,
                                    LocalDate birthDate,
                                    String jobPosition,
                                    Boolean isActive) {


}
