package org.jjjs.infrastructure.adapters.in.rest.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.jjjs.application.port.command.UpdateEmployeeCommand;
import org.jjjs.infrastructure.adapters.in.rest.dto.UpdateEmployeeRequest;

import java.util.function.Function;

@ApplicationScoped
public class UpdateEmployeeRequestToCommand implements Function<UpdateEmployeeRequest, UpdateEmployeeCommand> {

    @Override
    public UpdateEmployeeCommand apply(UpdateEmployeeRequest updateEmployeeRequest) {
        return new UpdateEmployeeCommand(
                updateEmployeeRequest.getFirstName(),
                updateEmployeeRequest.getSecondName(),
                updateEmployeeRequest.getLastName(),
                updateEmployeeRequest.getSecondLastName(),
                updateEmployeeRequest.getGender(),
                updateEmployeeRequest.getBirthDate(),
                updateEmployeeRequest.getJobPosition(),
                updateEmployeeRequest.getIsActive()
        );
    }
}
