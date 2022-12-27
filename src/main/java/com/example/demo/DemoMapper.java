package com.example.demo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DemoMapper {

  @Select("SELECT * FROM MASTER")
  List<CommonColumn> findMaster();
  @Select("SELECT * FROM H2")
  List<CommonColumn> findH2();
  @Select("SELECT * FROM POSTGRESQL")
  List<CommonColumn> findPostgres();
  @Select("SELECT * FROM MYSQL")
  List<CommonColumn> findMySql();
}
