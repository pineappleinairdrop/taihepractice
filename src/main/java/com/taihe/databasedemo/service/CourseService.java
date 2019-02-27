package com.taihe.databasedemo.service;

import com.taihe.databasedemo.dao.CourseMapper;
import com.taihe.databasedemo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    public List<Course> courseList4Student(Integer sid) {
        return courseMapper.selectExceptTaken(sid);
    }

    public List<Course> courseList() {
        return courseMapper.selectAll();
    }

    public void modifyCourse(Course course){
        courseMapper.updateByPrimaryKeySelective(course);
    }

    public void deleteCourseById(Integer id){
        courseMapper.deleteByPrimaryKey(id);
    }
}
