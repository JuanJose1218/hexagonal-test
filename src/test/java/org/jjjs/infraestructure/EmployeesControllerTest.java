package org.jjjs.infraestructure;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.jjjs.application.port.in.*;
import org.jjjs.domain.exceptions.EntityNotFoundException;
import org.jjjs.domain.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@QuarkusTest
public class EmployeesControllerTest {

    @InjectMock
    CreateEmployeeUseCase createEmployeeUseCase;
    @InjectMock
    GetEmployeesUseCase getEmployeesUseCase;
    @InjectMock
    DeleteEmployeesUseCase deleteEmployeesUseCase;

    @InjectMock
    UpdateEmployeeUseCase updateEmployeeUseCase;

    @Test
    void shouldReturn201WhenEmployeeIsCreated() {

        when(createEmployeeUseCase.create(any()))
                .thenReturn(new CreateEmployeeResponse("guardado exitoso"));

        given()
                .contentType(ContentType.JSON)
                .body("""
                      [   {\
                                "firstName": "Juan",
                                "secondName": "Pablo",
                                "lastName": "Perez",
                                "secondLastName": "Flores",
                                "age": "18",
                                "gender": "M",
                                "birthDate": "05-04-1993",
                                "jobPosition": "Dev"
                            }]\
                       \s""")
                .when()
                .post("/employees")
                .then()
                .statusCode(201);
    }
    @Test
    void shouldReturn400WhenEmployeeIsBadRequest() {

        when(createEmployeeUseCase.create(any())).thenThrow(new ConstraintViolationException(Collections.emptySet()));

        given()
                .contentType(ContentType.JSON)
                .body("[{\"name\": \"Juan\", \"age\": 25}]")
                .when()
                .post("/employees")
                .then()
                .statusCode(400);
    }

    @Test
    void shouldReturn200WhenGetAllEmployees() {
        when(getEmployeesUseCase.getAllEmployees()).thenReturn(Collections.emptyList());

        given()
                .when()
                .get("/employees")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn200WhenGetEmployeeByName() {
        var mockName = "Juan";
        when(getEmployeesUseCase.getEmployeesByName(mockName)).thenReturn(Collections.emptyList());

        given()
                .when()
                .queryParam("name", mockName)
                .get("/employees/search")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn200WhenGetEmployeeById() {
        var mockId = 1L;

        when(getEmployeesUseCase.getEmployeeById(mockId)).thenReturn(new GetEmployeesResponse(new Employee()));

        given()
                .when()
                .pathParam("id", mockId)
                .get("/employees/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn404WhenGetEmployeeById() {
        var mockId = 99L;
        when(getEmployeesUseCase.getEmployeeById(mockId))
                .thenThrow(new EntityNotFoundException("Employee not found"));

        given()
                .pathParam("id", mockId)
                .when()
                .get("/employees/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn200WhenDeleteEmployeeById() {
        var mockId = 1L;
        doNothing().when(deleteEmployeesUseCase).deleteById(mockId);
        given()
                .when()
                .pathParam("id", mockId)
                .delete("/employees/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    void shouldReturn404WhenDeleteEmployeeById() {
        var mockId = 99L;
        doThrow(new EntityNotFoundException("Employee not found"))
                .when(deleteEmployeesUseCase).deleteById(mockId);

        given()
                .pathParam("id", mockId)
                .when()
                .delete("/employees/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn204WhenUpdateEmployee() {
        var mockId = 1L;
        doNothing().when(updateEmployeeUseCase).updateById(anyLong(), any());

        given()
                .contentType(ContentType.JSON)
                .body("""
                         {\
                                 "firstName": "Juan",
                                 "secondName": "Pablo",
                                 "lastName": "Perez",
                                 "secondLastName": "Flores",
                                 "gender": "M",
                                 "birthDate": "05-04-1993",
                                 "jobPosition": "Dev"
                             }\
                        \s""")
                .when()
                .pathParam("id", mockId)
                .put("/employees/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldReturn404WhenUpdateEmployee() {
        var mockId = 1L;
        when(getEmployeesUseCase.getEmployeeById(mockId))
                .thenThrow(new EntityNotFoundException("Employee not found"));

        given()
                .contentType(ContentType.JSON)
                .body("""
                         {\
                                 "firstName": "Juan",
                                 "secondName": "Pablo",
                                 "lastName": "Perez",
                                 "secondLastName": "Flores",
                                 "gender": "M",
                                 "birthDate": "05-04-1993",
                                 "jobPosition": "Dev"
                             }\
                        \s""")
                .when()
                .pathParam("id", mockId)
                .put("/employees/{id}")
                .then()
                .statusCode(204);
    }

}