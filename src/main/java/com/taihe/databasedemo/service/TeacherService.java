package com.taihe.databasedemo.service;

import com.taihe.databasedemo.dao.CourseMapper;
import com.taihe.databasedemo.dao.TeachMapper;
import com.taihe.databasedemo.dao.TeacherMapper;
import com.taihe.databasedemo.entity.Course;
import com.taihe.databasedemo.entity.Teach;
import com.taihe.databasedemo.entity.Teacher;
import com.taihe.databasedemo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeachMapper teachMapper;

    public Teacher toValidate(Teacher teacher) {
        return teacherMapper.toValidate(teacher);
    }

    public List<Teacher> getTeacherList(int start, int end) {

        return teacherMapper.selectByPage(start + 1, end);
    }

    public void addTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        int nextId = teacherMapper.maxId() + 1;
        teacher.setId(nextId);

        String idStr = new DecimalFormat("0000000").format(nextId);
        String loginId = "1" + idStr;
        teacher.setLoginId(Long.valueOf(loginId));
        try {
            teacher.setPassword(Md5Util.encodeByMd5(String.valueOf(teacher.getLoginId())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        teacherMapper.insertSelective(teacher);
    }

    public List<Course> getCourseListByTid(Integer tid) {
        return courseMapper.getCourseListByTid(tid);
    }

    public void deleteTeach(Integer tid, Integer cid) {

        teachMapper.deleteByTidAndCid(tid, cid);
    }

    public Teacher getTeacherByLoginId(Long loginId) {
        return teacherMapper.getTeacherByLoginId(loginId);
    }

    public boolean canModifyPassword(Long loginId, String oldpwd, String password) throws NoSuchAlgorithmException {
        Teacher teacher = new Teacher();
        teacher.setLoginId(loginId);
        teacher.setPassword(Md5Util.encodeByMd5(oldpwd));
        Teacher result = teacherMapper.toValidate(teacher);
        if (result == null) {
            return false;
        } else {
            teacher.setPassword(Md5Util.encodeByMd5(password));
            teacherMapper.updatePasswordByLoginId(teacher);
            return true;
        }
    }

    public void addTeach(Integer sid, Integer cid) {
        Teach teach=new Teach();
        teach.setTid(sid);
        teach.setCid(cid);
        teachMapper.insertSelective(teach);
    }
}
