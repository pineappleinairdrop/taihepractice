package com.taihe.databasedemo.controller;

import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.service.StudentService;
import com.taihe.databasedemo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/studentloginAction")

    public String login(Student student, Map<String, String> map, HttpSession httpSession) {

        try {
            student.setPassword(Md5Util.encodeByMd5(student.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Student validate = studentService.toValidate(student);
        if (validate != null) {
            httpSession.setAttribute("userId", student.getLoginId());
            httpSession.removeAttribute("warnings");
            map.put("name", validate.getName());
            map.put("loginId", validate.getLoginId().toString());
            map.put("sex", validate.getSex());
            map.put("registerDate", String.valueOf(validate.getRegisterDate().getYear() + 1900));
            map.put("tuition", String.valueOf(validate.getTuition()));
            return "home";
        } else {
            httpSession.removeAttribute("warnings");
            map.put("notice", "请输入正确的账号与密码！");
            map.put("failname", String.valueOf(student.getLoginId()));
            return "studentlogin";
        }
    }
}
