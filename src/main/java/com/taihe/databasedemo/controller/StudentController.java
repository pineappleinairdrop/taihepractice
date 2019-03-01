package com.taihe.databasedemo.controller;

import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.service.CourseService;
import com.taihe.databasedemo.service.StudentService;
import com.taihe.databasedemo.utils.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class StudentController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;

    @PostMapping(value = "/studentloginAction")
    public String login(Student student, Map<String, String> map, HttpSession httpSession) {

        try {
            student.setPassword(Md5Util.encodeByMd5(student.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Student validate = studentService.toValidate(student);
        httpSession.removeAttribute("warnings");
        if (validate != null) {
            httpSession.setAttribute("stuId", validate.getId());
            map.put("name", validate.getName());
            map.put("loginId", validate.getLoginId().toString());
            map.put("sex", validate.getSex());
            map.put("registerDate", String.valueOf(validate.getRegisterDate().getYear() + 1900));
            map.put("tuition", String.valueOf(validate.getTuition()));
            return "home";
        } else {
            map.put("notice", "请输入正确的账号与密码！");
            map.put("failname", String.valueOf(student.getLoginId()));
            return "studentlogin";
        }
    }

    @GetMapping("/courseList")
    public String courseList(HttpSession httpSession, Model model) {
        Integer sid = (Integer) httpSession.getAttribute("stuId");
        model.addAttribute("courseList", studentService.getCourseList(sid));
        return "courseList";
    }

    @GetMapping("/deleteTaken/{cid}")
    public String deleteTaken(@PathVariable("cid") Integer cid, HttpSession session) {
        studentService.deleteTake((Integer) session.getAttribute("stuId"), cid);
        return "redirect:/courseList";

    }

    @PostMapping("/doModifyPassword")
    public String doModifyPassword(Long loginId, String oldpwd, String password, HttpSession httpSession, Map<String, String> map) throws NoSuchAlgorithmException {
        Student student = studentService.getStudentByLoginId(loginId);
        if (studentService.canModifyPassword(loginId, oldpwd, password)) {
            httpSession.setAttribute("modified", "succeeded");
            map.put("name", student.getName());
            map.put("loginId", student.getLoginId().toString());
            map.put("sex", student.getSex());
            map.put("registerDate", String.valueOf(student.getRegisterDate().getYear() + 1900));
            map.put("tuition", String.valueOf(student.getTuition()));

        } else {
            httpSession.setAttribute("modified", "failed");
            map.put("name", student.getName());
            map.put("loginId", student.getLoginId().toString());
            map.put("sex", student.getSex());
            map.put("registerDate", String.valueOf(student.getRegisterDate().getYear() + 1900));
            map.put("tuition", String.valueOf(student.getTuition()));

        }
        return "home";
    }
    @GetMapping("/stulogout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("stuId");
        httpSession.removeAttribute("warnings");
        return "studentlogin";
    }

}
