package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher getTeacherByLoginId(Long loginId);

    void updatePasswordByLoginId(Teacher record);

    Teacher toValidate(Teacher record);

    List<Teacher> selectByPage(@Param("start") int start, @Param("end") int end);

    int maxId();
}