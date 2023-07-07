package com.gabriel.api.integrationTests.testContainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ApplicationContextFailureProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer postgre = new PostgreSQLContainer("postgres:15.3");

        private static void startContainers(){
            Startables.deepStart(Stream.of(postgre)).join();
        }

        private static Map<String,String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url",postgre.getJdbcUrl(),
                    "spring.datasource.username",postgre.getUsername(),
                    "spring.datasource.password",postgre.getPassword()
            );
        }

        @SuppressWarnings({"unchecked","rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map)createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}
