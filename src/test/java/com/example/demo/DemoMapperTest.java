package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class DemoMapperTest {

  @Autowired
  DemoMapper mapper;

  @ActiveProfiles("h2")
  @Nested
  class H2 {

    @Test
    void test_01() {
        mapper.findH2();
        assertThatThrownBy(() -> mapper.findPostgres()).isInstanceOf(DataAccessException.class);
    }
  }

  @ActiveProfiles("postgres")
  @Nested
  class Postgres {



    @Test
    void test_01() {
      mapper.findPostgres();
      assertThatThrownBy(() -> mapper.findH2()).isInstanceOf(DataAccessException.class);
    }
  }
}