package com.taihe.databasedemo.service;

import com.taihe.databasedemo.dao.RedundancyTakeMapper;
import com.taihe.databasedemo.dao.StudentMapper;
import com.taihe.databasedemo.dao.TakeMapper;
import com.taihe.databasedemo.entity.RedundancyTake;
import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.entity.Take;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    RedundancyTakeMapper redundancyTakeMapper;
    @Autowired
    TakeMapper takeMapper;

    public Student toValidate(Student user) {

        return studentMapper.toValidate(user);
    }

    public List<RedundancyTake> getCourseList(Integer sid) {
        return redundancyTakeMapper.selectCourseListBySid(sid);
    }

    public List<Student> getStudentList() {

        return studentMapper.selectByPage();//todo: pageHelper
    }


    @Transactional
    public void deleteTake(Integer sid, Integer cid) {
        redundancyTakeMapper.deleteBySidAndCid(sid, cid);
        takeMapper.deleteBySidAndCid(sid, cid);
    }

    @Transactional
    public void addTake(Integer sid, Integer cid) {
        Take take = new Take();
        take.setSid(sid);
        take.setCid(cid);
        takeMapper.insertSelective(take);
        redundancyTakeMapper.insertBySidAndCid(sid, cid);
    }

}
