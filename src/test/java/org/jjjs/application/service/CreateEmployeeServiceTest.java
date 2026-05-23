package org.jjjs.application.service;

import org.jjjs.application.port.command.CreateEmployeeCommand;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.domain.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateEmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private CreateEmployeeService createEmployeeService;

    @BeforeEach
    void setUp() {
        createEmployeeService = new CreateEmployeeService(employeeRepository);
    }

    @Test
    void shouldCreateEmployeeWhenDataIsValid() {

        var mockEmployeesCommand = List.of(
                new CreateEmployeeCommand("Juan", "Carlos", "Pérez", "Gómez",
                        30, "M", LocalDate.of(1996, 5, 20),
                        "Java Developer"),
                new CreateEmployeeCommand(
                        "Pablo", "Carlos", "Sanchez", "Lopez",
                        30, "M", LocalDate.of(1996, 5, 20),
                        "QA")

        );


        doNothing().when(employeeRepository).saveAll(anyList());

        var result = createEmployeeService.create(mockEmployeesCommand);

        assertEquals("guradado exitoso", result.getMessage());
        verify(employeeRepository, times(1)).saveAll(anyList());

    }

    @Test
    void shouldCreateEmployeeWhenDataIsNotValid() {

        var mockEmployeesCommand = List.of(
                new CreateEmployeeCommand("Juan", "Carlos", "Pérez", "Gómez",
                        15, "M", LocalDate.of(1996, 5, 20),
                        "Java Developer"),
                new CreateEmployeeCommand(
                        "Pablo", "Carlos", "Sanchez", "Lopez",
                        30, "M", LocalDate.of(1996, 5, 20),
                        "QA")

        );


        var exception = assertThrows(BusinessRuleException.class, () -> {
            createEmployeeService.create(mockEmployeesCommand);
        });


        assertEquals("The batch contains underage employees.", exception.getMessage());


        verify(employeeRepository, never()).saveAll(anyList());


    }
}
