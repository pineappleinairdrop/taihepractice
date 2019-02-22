package com.taihe.databasedemo.service;


import com.taihe.databasedemo.dao.StudentMapper;
import com.taihe.databasedemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    StudentMapper studentMapper;
    public Student getPeople(int a){
        return studentMapper.selectByPrimaryKey(a);
    }
}
