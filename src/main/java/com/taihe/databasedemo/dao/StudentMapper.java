package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student toValidate(Student record);

    List<Student> selectByPage(@Param("start") int start,@Param("end")int end);

    void updatePasswordByLoginId(Student record);

    int maxId();

    Student selectByLoginId(@Param("loginId") Long loginId);
}