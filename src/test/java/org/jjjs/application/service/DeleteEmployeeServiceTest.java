package org.jjjs.application.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteEmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    private DeleteEmployeeService deleteEmployeeService;

    @BeforeEach
    void setUp() {
        deleteEmployeeService = new DeleteEmployeeService(employeeRepository);
    }


    @Test
    void shouldDeleteEmployeeWhenEmployeeExist() {
        var employeeId = 1L;
        var mockEmployee = new Employee(
                employeeId, "Juan", "Carlos", "Pérez", "Gómez",
                30, "M", LocalDate.of(1996, 5, 20),
                "Java Developer", LocalDateTime.now(), true
        );

        when(employeeRepository.getById(employeeId)).thenReturn(mockEmployee);
        doNothing().when(employeeRepository).deleteById(employeeId);

        deleteEmployeeService.deleteById(employeeId);

        verify(employeeRepository, times(1)).getById(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void shouldDeleteEmployeeWhenEmployeeNotExist() {
        Long nonExistentId = 99L;


        when(employeeRepository.getById(nonExistentId))
                .thenThrow(new EntityNotFoundException("Employee not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            deleteEmployeeService.deleteById(nonExistentId);
        });


        assertEquals("Employee not found", exception.getMessage());

        verify(employeeRepository, times(1)).getById(nonExistentId);
        verifyNoMoreInteractions(employeeRepository);
    }

}
