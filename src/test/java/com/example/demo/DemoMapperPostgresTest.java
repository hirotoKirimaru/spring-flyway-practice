package com.example.demo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
@Testcontainers
@ActiveProfiles("postgres")
class DemoMapperPostgresTest {

  @Autowired
  DemoMapper mapper;

  @Container
  public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      DockerImageName.parse("postgres"))
      .withUsername("user")
      .withPassword("pass")
      .withDatabaseName("database")
//			.withUsername("devuser")
//			.withPassword("devuser")
//			.withDatabaseName("devdb");
      ;

  @DynamicPropertySource
  static void setup(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url",
        postgres::getJdbcUrl); // コンテナで起動中のPostgresへ接続するためのJDBC URLをプロパティへ設定
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }


  @Test
  void test_01() {
    assertThatThrownBy(() -> mapper.findMaster()).isInstanceOf(DataAccessException.class);
    mapper.findPostgres();
    assertThatThrownBy(() -> mapper.findH2()).isInstanceOf(DataAccessException.class);
    assertThatThrownBy(() -> mapper.findMySql()).isInstanceOf(DataAccessException.class);
  }
}