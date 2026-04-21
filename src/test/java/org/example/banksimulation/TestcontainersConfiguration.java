package org.example.banksimulation;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration { // Must be public

    @Bean
    @ServiceConnection
    public PostgreSQLContainer postgresContainer() { // Removed <?> and added public
        return new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));
    }
}