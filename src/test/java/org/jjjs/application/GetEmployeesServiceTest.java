package org.jjjs.application;


import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.application.service.GetEmployeesService;
import org.jjjs.domain.exceptions.EntityNotFoundException;
import org.jjjs.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetEmployeesServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;
    private GetEmployeesService getEmployeesService;

    @BeforeEach
    void setUp() {
        getEmployeesService = new GetEmployeesService(employeeRepository);
    }

    @Test
    void shouldReturnEmployeeResponseWhenEmployeeExists() {

        Long employeeId = 1L;
        Employee mockEmployee = new Employee(
                employeeId, "Juan", "Carlos", "Pérez", "Gómez",
                30, "M", LocalDate.of(1996, 5, 20),
                "Java Developer", LocalDateTime.now(), true
        );

        when(employeeRepository.getById(employeeId)).thenReturn(mockEmployee);


        var result = getEmployeesService.getEmployeeById(employeeId);


        assertNotNull(result, "La respuesta no debería ser nula");
        assertEquals(employeeId, result.id());
        assertEquals("Juan", result.firstName());
        assertEquals("Java Developer", result.jobPosition());

        verify(employeeRepository, times(1)).getById(employeeId);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {

        Long nonExistentId = 99L;


        when(employeeRepository.getById(nonExistentId))
                .thenThrow(new EntityNotFoundException("Employee not found"));


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            getEmployeesService.getEmployeeById(nonExistentId);
        });


        assertEquals("Employee not found", exception.getMessage());


        verify(employeeRepository, times(1)).getById(nonExistentId);
    }
}
