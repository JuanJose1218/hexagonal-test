package org.jjjs.domain;

import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.domain.exceptions.BusinessRuleException;
import org.jjjs.domain.model.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    @Test
    void shouldCreateEmployeeWhenAgeIsLegal() {

        Employee employee = new Employee(
                "Juan", "Carlos", "Pérez", "Gómez",
                30, "M", LocalDate.of(1996, 5, 20),
                "Java Developer");
        new Employee(
                "Pablo", "Carlos", "Sanchez", "Lopez",
                30, "M", LocalDate.of(1996, 5, 20),
                "QA");


        assertNotNull(employee);
        assertEquals("Juan", employee.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeIsUnderage() {

        var exception = assertThrows(BusinessRuleException.class, () -> {
            new Employee(new CreateEmployeeCommand("Juan", "Carlos", "Pérez", "Gómez",
                    15, "M", LocalDate.of(1996, 5, 20),
                    "Java Developer"));
        });

        assertEquals("The batch contains underage employees.", exception.getMessage());
    }
}
