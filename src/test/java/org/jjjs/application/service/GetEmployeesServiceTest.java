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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        var employeeId = 1L;
        var mockEmployee = new Employee(
                employeeId, "Juan", "Carlos", "Pérez", "Gómez",
                30, "M", LocalDate.of(1996, 5, 20),
                "Java Developer", LocalDateTime.now(), true
        );

        when(employeeRepository.getById(employeeId)).thenReturn(Optional.of(mockEmployee));


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

    @Test
    void shouldReturnEmployeesListWhenEmployeesExists() {
        var mockEmployees = List.of(
                new Employee(
                        1L, "Juan", "Carlos", "Pérez", "Gómez",
                        30, "M", LocalDate.of(1996, 5, 20),
                        "Java Developer", LocalDateTime.now(), true),
                new Employee(
                        2L, "Pablo", "Carlos", "Sanchez", "Lopez",
                        30, "M", LocalDate.of(1996, 5, 20),
                        "QA", LocalDateTime.now(), true)

        );

        when(employeeRepository.getAll()).thenReturn(mockEmployees);

        var result = getEmployeesService.getAllEmployees();
        assertNotNull(result, "La respuesta no debería ser nula");
        assertEquals(2, result.size(), "La respuesta debe tener 2 elimentos");


        verify(employeeRepository, times(1)).getAll();


    }

    @Test
    void shouldReturnEmployeesListWhenEmployeesNotExists() {
        List<Employee> mockEmployees = Collections.emptyList();

        when(employeeRepository.getAll()).thenReturn(mockEmployees);

        var result = getEmployeesService.getAllEmployees();
        assertNotNull(result, "La respuesta no debería ser nula");
        assertEquals(0, result.size(), "La respuesta debe tener 0 elementos");
        assertTrue(result.isEmpty());


        verify(employeeRepository, times(1)).getAll();


    }

    @Test
    void shouldReturnEmployeesListByNameWhenEmployeesExists() {
        var name = "Juan";
        var mockEmployees = List.of(
                new Employee(
                        1L, "Juan", "Carlos", "Pérez", "Gómez",
                        30, "M", LocalDate.of(1996, 5, 20),
                        "Java Developer", LocalDateTime.now(), true),
                new Employee(
                        2L, "Juan", "Pablo", "Sanchez", "Lopez",
                        35, "M", LocalDate.of(1991, 3, 25),
                        "QA", LocalDateTime.now(), true)

        );

        when(employeeRepository.getByName(name)).thenReturn(mockEmployees);

        var result = employeeRepository.getByName(name);
        var resultName = result.stream().findFirst().map(Employee::getFirstName).orElse("");

        assertNotNull(result, "La respuesta no debería ser nula");
        assertEquals(2, result.size(), "La respuesta debe tener 2 elimentos");
        assertEquals("Juan", resultName);
        assertNotEquals("Pedro", resultName);
        assertFalse(result.isEmpty());


        verify(employeeRepository, times(1)).getByName(name);


    }

    @Test
    void shouldReturnEmployeesListByNameWhenEmployeesNotExists() {
        var name = "Juan";
        List<Employee> mockEmployees = Collections.emptyList();

        when(employeeRepository.getByName(name)).thenReturn(mockEmployees);

        var result = employeeRepository.getByName(name);
        var resultName = result.stream().findFirst().map(Employee::getFirstName).orElse("");
        assertNotNull(result, "La respuesta no debería ser nula");
        assertEquals(0, result.size(), "La respuesta debe tener 0 elementos");
        assertEquals("", resultName);
        assertTrue(result.isEmpty());


        verify(employeeRepository, times(1)).getByName(name);


    }
}
