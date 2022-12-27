package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class DemoApplicationTests {

  // TODO: テストコンテナー使うときは継承しないとダメ。テストクラスごとに再構築することになる。
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
    registry.add("spring.datasource.url", postgres::getJdbcUrl); // コンテナで起動中のPostgresへ接続するためのJDBC URLをプロパティへ設定
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Test
  void contextLoads() {
  }

}
