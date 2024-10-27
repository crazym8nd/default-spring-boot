package com.spring.crud.rest;

import com.spring.crud.exception.ExceptionResponse;
import com.spring.crud.model.request_dto.StudentForRequest;
import com.spring.crud.model.request_dto.StudentForUpdate;
import com.spring.crud.model.response_dto.InformationResponse;
import com.spring.crud.model.response_dto.StudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ImportTestcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @ServiceConnection
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    @DynamicPropertySource
    static void postgresqlProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

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

    @Test
    void givenStudentId_whenGetStudent_thenSuccessResponse() {
        //given
        final StudentForRequest student = new StudentForRequest("testName", "test@email.com");
        final WebTestClient.ResponseSpec createResult = webTestClient.post().uri("/api/v1/students")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .bodyValue(student)
                                                                     .exchange();

        createResult.expectStatus().isCreated()
                    .expectBody(StudentResponse.class)
                    .consumeWith(response -> {
                        final StudentResponse createdStudent = response.getResponseBody();
                        assertNotNull(createdStudent);

                        // when
                        final WebTestClient.ResponseSpec getResult = webTestClient
                                .get()
                                .uri("/api/v1/students/" + createdStudent.id())
                                .exchange();

                        // then
                        getResult.expectStatus().isOk()
                                 .expectBody(StudentResponse.class)
                                 .consumeWith(getResponse -> {
                                     final StudentResponse retrievedStudent = getResponse.getResponseBody();
                                     assertNotNull(retrievedStudent);
                                     assertEquals(createdStudent.id(), retrievedStudent.id());
                                     assertEquals(createdStudent.name(), retrievedStudent.name());
                                     assertEquals(createdStudent.email(), retrievedStudent.email());
                                 });
                    });
    }

    @Test
    void givenStudents_whenGetAllStudents_thenSuccessResponse() {
        //given
        final StudentForRequest student1 = new StudentForRequest("test1Name", "test1@email.com");
        final StudentForRequest student2 = new StudentForRequest("test2Name", "test2@email.com");
        final WebTestClient.ResponseSpec createFirstStudent = webTestClient.post().uri("/api/v1/students")
                                                                           .contentType(MediaType.APPLICATION_JSON)
                                                                           .bodyValue(student1)
                                                                           .exchange();
        final WebTestClient.ResponseSpec createSecondStudent = webTestClient.post().uri("/api/v1/students")
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .bodyValue(student2)
                                                                            .exchange();
        createFirstStudent.expectStatus().isCreated();
        createSecondStudent.expectStatus().isCreated();


        final WebTestClient.ResponseSpec result = webTestClient.get().uri("/api/v1/students")
                                                               .exchange();

        result.expectStatus().isOk()
              .expectBodyList(StudentResponse.class)
              .consumeWith(resultResponse -> {
                  final List<StudentResponse> students = resultResponse.getResponseBody();
                  assertNotNull(students);
                  assertEquals(2, students.size());

                  // Verify the first student
                  final StudentResponse firstStudent = students.stream()
                                                               .filter(student -> student
                                                                       .email()
                                                                       .equals(student1.email()))
                                                               .findFirst()
                                                               .orElse(null);
                  assertNotNull(firstStudent);
                  assertEquals(student1.name(), firstStudent.name());
                  assertEquals(student1.email(), firstStudent.email());

                  // Verify the second student
                  final StudentResponse secondStudent = students.stream()
                                                                .filter(student -> student
                                                                        .email()
                                                                        .equals(student2.email()))
                                                                .findFirst()
                                                                .orElse(null);
                  assertNotNull(secondStudent);
                  assertEquals(student2.name(), secondStudent.name());
                  assertEquals(student2.email(), secondStudent.email());
              });
    }

    @Test
    void givenStudentId_whenGetStudent_thenNotFoundResponse() {
        //given
        final Long nonExistentId = Long.MAX_VALUE;

        final ExceptionResponse expectedExceptionResponse = new ExceptionResponse(
                404,
                "Not Found",
                "Student with ID " + nonExistentId + " not found",
                "/api/v1/students/" + nonExistentId
        );

        // when
        final WebTestClient.ResponseSpec getResult = webTestClient.get().uri("/api/v1/students/" + nonExistentId)
                                                                  .exchange();

        // then
        getResult.expectStatus().isNotFound()
                 .expectBody(ExceptionResponse.class)
                 .consumeWith(resultResponse -> {
                     final ExceptionResponse exceptionResponse = resultResponse.getResponseBody();
                     assertNotNull(exceptionResponse);
                     assertNotNull(exceptionResponse.getTimestamp());
                     assertEquals(exceptionResponse.getStatus(), expectedExceptionResponse.getStatus());
                     assertEquals(exceptionResponse.getMessage(), exceptionResponse.getMessage());
                     assertEquals(exceptionResponse.getPath(), expectedExceptionResponse.getPath());
                 });
    }

    @Test
    void givenStudentForUpdate_whenUpdateStudent_thenSuccessResponse() {
        // Given
        final StudentForRequest student = new StudentForRequest("testName", "test@email.com");
        final Long createdStudentId = webTestClient.post().uri("/api/v1/students")
                                                   .contentType(MediaType.APPLICATION_JSON)
                                                   .bodyValue(student)
                                                   .exchange()
                                                   .expectStatus().isCreated()
                                                   .expectBody(StudentResponse.class)
                                                   .returnResult()
                                                   .getResponseBody()
                                                   .id();

        // Given - Student for update
        final StudentForUpdate updatedStudent = new StudentForUpdate("updatedName", "updated@email.com");

        // When - Update the student
        final WebTestClient.ResponseSpec updateStudent = webTestClient.put().uri("/api/v1/students/" + createdStudentId)
                                                                      .contentType(MediaType.APPLICATION_JSON)
                                                                      .bodyValue(updatedStudent)
                                                                      .exchange();

        // Then - Verify the student is updated successfully
        updateStudent.expectStatus().isOk()
                     .expectBody(StudentResponse.class)
                     .consumeWith(response -> {
                         final StudentResponse updatedResponse = response.getResponseBody();
                         assertNotNull(updatedResponse);
                         assertEquals(createdStudentId, updatedResponse.id());
                         assertEquals(updatedStudent.name(), updatedResponse.name());
                         assertEquals(updatedStudent.email(), updatedResponse.email());
                     });
    }

    @Test
    void givenStudentForUpdate_whenUpdateStudent_thenNotFoundResponse() {
        // Given - Student for update
        final Long nonExistentId = Long.MAX_VALUE;
        final StudentForUpdate updatedStudent = new StudentForUpdate("updatedName", "updated@email.com");

        // When - Update the student
        final WebTestClient.ResponseSpec updateStudent = webTestClient.put().uri("/api/v1/students/" + nonExistentId)
                                                                      .contentType(MediaType.APPLICATION_JSON)
                                                                      .bodyValue(updatedStudent)
                                                                      .exchange();

        // Then - Verify the student is updated successfully
        updateStudent.expectStatus().isNotFound();
    }

    @Test
    void givenStudentForDelete_whenDeleteStudent_thenSuccessResponse() {
        // Given
        final String expectedMessage = "Student deleted successfully";
        final StudentForRequest student = new StudentForRequest("sutedntForeDelete", "testdelete@email.com");
        final Long createdStudentId = webTestClient.post().uri("/api/v1/students")
                                                   .contentType(MediaType.APPLICATION_JSON)
                                                   .bodyValue(student)
                                                   .exchange()
                                                   .expectStatus().isCreated()
                                                   .expectBody(StudentResponse.class)
                                                   .returnResult()
                                                   .getResponseBody()
                                                   .id();
        // When - Update the student
        final WebTestClient.ResponseSpec deleteStudent = webTestClient
                .delete()
                .uri("/api/v1/students/" + createdStudentId)
                .exchange();

        // Then - Verify the student is updated successfully
        deleteStudent.expectStatus().isOk()
                     .expectBody(InformationResponse.class)
                     .consumeWith(response -> {
                         final InformationResponse deletedResponse = response.getResponseBody();
                         assertNotNull(deletedResponse);
                         assertEquals(expectedMessage, deletedResponse.message());
                     });
    }

    @Test
    void givenStudentForDelete_whenDeleteStudent_thenNotFoundResponse() {
        // Given - Student for update
        final Long nonExistentId = Long.MAX_VALUE;

        // When - Update the student
        final WebTestClient.ResponseSpec deleteStudent = webTestClient.delete().uri("/api/v1/students/" + nonExistentId)
                                                                      .exchange();

        // Then - Verify the student is updated successfully
        deleteStudent.expectStatus().isNotFound();
    }


}