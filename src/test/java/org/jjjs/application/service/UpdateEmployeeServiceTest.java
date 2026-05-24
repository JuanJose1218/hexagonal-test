package org.jjjs.application.service;

import org.jjjs.application.port.command.UpdateEmployeeCommand;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.domain.exceptions.EntityNotFoundException;
import org.jjjs.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateEmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private UpdateEmployeeService updateEmployeeService;

    @BeforeEach
    void setUp() {
        updateEmployeeService = new UpdateEmployeeService(employeeRepository);
    }

    @Test
    void shouldUpdateEmployee() {
        var idEmployee = 1L;
        var employeeMock = new Employee(
                1L, "Juan", "Carlos", "Pérez", "Gómez",
                30, "M", LocalDate.of(1996, 5, 20),
                "Java Developer", LocalDateTime.now(), true);
        var updateEmployeeCommandMock = new UpdateEmployeeCommand(
                "",
                "",
                "",
                "",
                "",
                null,
                null,
                false
        );

        doNothing().when(employeeRepository).updateById(employeeMock);
        when(employeeRepository.getById(idEmployee)).thenReturn(Optional.of(employeeMock));

        updateEmployeeService.updateById(idEmployee, updateEmployeeCommandMock);


        verify(employeeRepository, times(1)).getById(idEmployee);
        verify(employeeRepository, times(1)).updateById(employeeMock);

    }

    @Test
    void shouldUpdateEmployeeWhenEmployeeNotExist() {
        Long nonExistentId = 99L;


        when(employeeRepository.getById(nonExistentId))
                .thenThrow(new EntityNotFoundException("Employee not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            updateEmployeeService.updateById(nonExistentId, any());
        });


        assertEquals("Employee not found", exception.getMessage());

        verify(employeeRepository, times(1)).getById(nonExistentId);
        verifyNoMoreInteractions(employeeRepository);
    }

}
