package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
class DemoMapperTest {

  @Autowired
  DemoMapper mapper;


  @ActiveProfiles("postgres")
  @Nested
  class Postgres {

    @Test
    void test_01() {
      assertThat(
          mapper.findPostgres()
      ).isEmpty();
    }
  }
}