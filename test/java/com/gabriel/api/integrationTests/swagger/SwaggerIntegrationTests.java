package com.gabriel.api.integrationTests.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import com.gabriel.api.configs.TestsConfig;
import com.gabriel.api.integrationTests.testContainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage(){
        var content =
            given()
                    .basePath("/swagger-ui/index.html")
                    .port(TestsConfig.SERVER_PORT)
                    .when()
                        .get()
                    .then()
                        .statusCode(200)
                    .extract()
                        .body()
                            .asString();
        assertTrue(content.contains("Swagger UI"));
    }
}
