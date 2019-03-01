package com.taihe.databasedemo.service;

import com.taihe.databasedemo.dao.RedundancyTakeMapper;
import com.taihe.databasedemo.dao.StudentMapper;
import com.taihe.databasedemo.dao.TakeMapper;
import com.taihe.databasedemo.entity.RedundancyTake;
import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.entity.Take;
import com.taihe.databasedemo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
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

    public List<Student> getStudentList(int start,int end) {

        return studentMapper.selectByPage(start+1,end);
    }

    public boolean canModifyPassword(Long loginId,String oldpwd,String password) throws NoSuchAlgorithmException {
        Student student=new Student();
        student.setLoginId(loginId);
        student.setPassword(Md5Util.encodeByMd5(oldpwd));
        Student result=studentMapper.toValidate(student);
        if (result==null){
            return false;
        }
        else {
            student.setPassword(Md5Util.encodeByMd5(password));
            studentMapper.updatePasswordByLoginId(student);
            return true;
        }
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

    public void addStudent(Student student) {
        int maxId = studentMapper.maxId() + 1;
        student.setId(maxId);
        int date = student.getRegisterDate().getYear() + 1900;
        String maxIdStr = new DecimalFormat("0000000").format(maxId);
        String loginId = String.valueOf(date) + maxIdStr;
        student.setLoginId(Long.valueOf(loginId));
        try {
            student.setPassword(Md5Util.encodeByMd5(String.valueOf(student.getLoginId())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        studentMapper.insertSelective(student);
    }
    public int maxId(){
        return studentMapper.maxId();
    }
    public Student getStudentByLoginId(Long loginId){
        return studentMapper.selectByLoginId(loginId);
    }
}
