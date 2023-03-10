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
@ActiveProfiles("h2")
class DemoMapperTest {

  @Autowired
  DemoMapper mapper;

  @Test
  void test_01() {
    assertThatThrownBy(() -> mapper.findMaster()).isInstanceOf(DataAccessException.class);
    mapper.findH2();
    assertThatThrownBy(() -> mapper.findPostgres()).isInstanceOf(DataAccessException.class);
    assertThatThrownBy(() -> mapper.findMySql()).isInstanceOf(DataAccessException.class);
  }

}