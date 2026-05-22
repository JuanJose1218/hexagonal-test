package org.jjjs.application.port.in;

import org.jjjs.application.port.command.CreateEmployeeCommand;

import java.util.List;

public interface CreateEmployeeUseCase {
    CreateEmployeeResponse create(List<CreateEmployeeCommand> employeeCommands);
}
