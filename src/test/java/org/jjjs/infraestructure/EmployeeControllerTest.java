package org.jjjs.infraestructure;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.jjjs.application.port.in.CreateEmployeeResponse;
import org.jjjs.application.port.in.CreateEmployeeUseCase;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
public class EmployeeControllerTest {

    @InjectMock
    CreateEmployeeUseCase createEmployeeUseCase;


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
}
