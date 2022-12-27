package com.example.demo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DemoMapper {

  @Select("SELECT * FROM H2")
  List<Postgres> findH2();
  @Select("SELECT * FROM POSTGRESQL")
  List<Postgres> findPostgres();
}
