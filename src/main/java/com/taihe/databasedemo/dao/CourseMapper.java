package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    List<Course> selectExceptTaken(Integer sid);

    List<Course> selectAll();

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}