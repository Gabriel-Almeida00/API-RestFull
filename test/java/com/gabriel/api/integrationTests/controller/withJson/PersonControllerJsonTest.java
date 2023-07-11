package com.gabriel.api.integrationTests.controller.withJson;

import com.gabriel.api.configs.TestsConfig;
import com.gabriel.api.integrationTests.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonVO personVO;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        personVO = new PersonVO();
    }

    @Test
    @Order(1)
    public void testCreate() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestsConfig.HEADER_PARAM_ORIGIN, "https://erudio.com.br")
                .setBasePath("/api/person/v1")
                .setPort(TestsConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType(TestsConfig.CONTENT_TYPE_JSON)
                .body(personVO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
        personVO = createdPerson;

        assertNotNull(createdPerson);

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Richard",createdPerson.getFirstName());
        assertEquals("stallman",createdPerson.getLastName());
        assertEquals("New York City",createdPerson.getAddress());
        assertEquals("Male",createdPerson.getGender());
    }

    private void mockPerson() {
        personVO.setFirstName("Richard");
        personVO.setLastName("stallman");
        personVO.setAddress("New York City");
        personVO.setGender("Male");
    }
}
