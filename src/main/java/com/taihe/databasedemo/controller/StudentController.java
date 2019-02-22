package com.taihe.databasedemo.controller;

import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.service.StudentService;
import com.taihe.databasedemo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping(value = "/studentloginAction")

    public String login(Student user, Map<String, String> map, HttpSession httpSession) {

        try {
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Student student=studentService.toValidate(user);
        if (student!=null) {
            httpSession.setAttribute("userId", user.getLoginId());
            httpSession.removeAttribute("warnings");
            httpSession.setAttribute("User",student);
            return "redirect:/home";
        } else {
            httpSession.removeAttribute("warnings");
            map.put("notice", "请输入正确的账号与密码！");
            map.put("failname", String.valueOf(user.getLoginId()));
            return "studentlogin";
        }
    }
    @GetMapping("/home")
    public String home(HttpSession httpSession,Map<String,String> map,Student student)
    {
        student = (Student) httpSession.getAttribute("User");
        map.put("name",student.getName());
        map.put("sex",student.getSex());
        map.put("registerDate",String.valueOf(student.getRegisterDate()));
        System.out.println(map.get("registerDate"));
        map.put("tuition",String.valueOf(student.getTuition()));
        return "home";
    }
}
