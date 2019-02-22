package com.taihe.databasedemo.service;

import com.taihe.databasedemo.dao.StudentMapper;
import com.taihe.databasedemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student toValidate(Student user) {

        return studentMapper.toValidate(user);
    }
}
