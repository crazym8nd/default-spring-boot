package com.spring.crud.rest;

import com.spring.crud.model.StudentForRequest;
import com.spring.crud.model.StudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ImportTestcontainers
class StudentControllerIT {
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    );
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void givenStudent_whenCreateStudent_thenSuccessResponse() {
        //given
        final StudentForRequest student = new StudentForRequest("testName", "test@email.com");

        //when
        final WebTestClient.ResponseSpec result = webTestClient.post().uri("/api/v1/students")
                                                               .contentType(MediaType.APPLICATION_JSON)
                                                               .bodyValue(student)
                                                               .exchange();
        //then
        result.expectStatus().isCreated()
              .expectBody(StudentResponse.class)
              .consumeWith(response -> {
                  final StudentResponse createdStudent = response.getResponseBody();
                  assertNotNull(createdStudent);
                  assertNotNull(createdStudent.id());
                  assertEquals(student.name(), createdStudent.name());
              });
    }

}