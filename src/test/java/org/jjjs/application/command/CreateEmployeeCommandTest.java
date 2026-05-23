package org.jjjs.application.command;

import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateEmployeeCommandTest {

    @Test
    void shouldCreateEmployeeCommand() {
        var command = new CreateEmployeeCommand("Juan",
                null,
                "Lopez",
                "Perez",
                "M",
                LocalDate.of(1995, 6, 6),
                "dev");

        assertEquals("Juan", command.firstName());
        assertNull(command.secondName());
        assertEquals("dev", command.jobPosition());

    }
}
