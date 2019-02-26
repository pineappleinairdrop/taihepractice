package com.taihe.databasedemo.service;

import com.taihe.databasedemo.dao.CourseMapper;
import com.taihe.databasedemo.dao.RedundancyTakeMapper;
import com.taihe.databasedemo.dao.TakeMapper;
import com.taihe.databasedemo.entity.Course;
import com.taihe.databasedemo.entity.RedundancyTake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    public List<Course> courseList(Integer sid){
        return courseMapper.selectExceptTaken(sid);
    }

}
