package com.gabriel.api.integrationTests.controller.withXaml;

import com.gabriel.api.configs.TestsConfig;
import com.gabriel.api.integrationTests.controller.withXaml.mapper.YamlMapper;
import com.gabriel.api.integrationTests.testContainers.AbstractIntegrationTest;
import com.gabriel.api.integrationTests.vo.AccountCredentialsVO;
import com.gabriel.api.integrationTests.vo.TokenVO;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerYamlTest extends AbstractIntegrationTest {

    public static TokenVO tokenVO;
    private static YamlMapper yamlMapper;
    @BeforeAll
    public static void setUp(){
        yamlMapper = new YamlMapper();
    }

    @Test
    @Order(1)
    public void testSignin() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin234");

        tokenVO = given()
                .config(RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig()
                                        .encodeContentTypeAs(
                                                TestsConfig.CONTENT_TYPE_YML, ContentType.TEXT)))
                .accept(TestsConfig.CONTENT_TYPE_YML)
                .basePath("/auth/signin")
                .port(TestsConfig.SERVER_PORT)
                .contentType(TestsConfig.CONTENT_TYPE_YML)
                .body(user, yamlMapper)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenVO.class, yamlMapper);

        assertNotNull(tokenVO.getAccessToken());
        assertNotNull(tokenVO.getRefreshToken());
    }

    @Test
    @Order(2)
    public void testRefresh() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin234");

        var newTokenVO = given()
                .config(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs(
                                        TestsConfig.CONTENT_TYPE_YML, ContentType.TEXT)))
                .accept(TestsConfig.CONTENT_TYPE_YML)
                .basePath("/auth/refresh")
                .port(TestsConfig.SERVER_PORT)
                .contentType(TestsConfig.CONTENT_TYPE_YML)
                .pathParams("username", tokenVO.getUsername())
                .header(TestsConfig.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO.getRefreshToken())
                .when()
                .put("{username}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenVO.class, yamlMapper);

        assertNotNull(newTokenVO.getAccessToken());
        assertNotNull(newTokenVO.getRefreshToken());
    }
}
