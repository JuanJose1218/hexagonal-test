package org.jjjs.application.mapper;


import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.domain.model.Employee;

public class EmployeeCommandToDomain  {

    public static Employee toDomain(CreateEmployeeCommand createEmployeeCommand){

        return new Employee(createEmployeeCommand.firstName(),
                createEmployeeCommand.secondName(),
                createEmployeeCommand.lastName(),
                createEmployeeCommand.secondLastName(),
                createEmployeeCommand.gender(),
                createEmployeeCommand.birthDate(),
                createEmployeeCommand.jobPosition());


    }
}
