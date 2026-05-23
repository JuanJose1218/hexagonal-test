package org.jjjs.application.port.in;

import org.jjjs.application.port.command.UpdateEmployeeCommand;

public interface UpdateEmployeeUseCase {

    void updateById(Long id, UpdateEmployeeCommand updateEmployeeRequest);
}
