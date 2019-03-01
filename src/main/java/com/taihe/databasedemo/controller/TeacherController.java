package com.taihe.databasedemo.controller;

import com.taihe.databasedemo.entity.Teacher;
import com.taihe.databasedemo.service.TeacherService;
import com.taihe.databasedemo.utils.Md5Util;
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
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/teacherloginAction")
    public String login(Teacher teacher, Map<String, String> map, HttpSession httpSession) {
        try {
            teacher.setPassword(Md5Util.encodeByMd5(teacher.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Teacher validate = teacherService.toValidate(teacher);
        httpSession.removeAttribute("warnings");
        if (validate != null) {
            httpSession.setAttribute("teaId", validate.getId());
            map.put("name", validate.getName());
            map.put("loginId", validate.getLoginId().toString());
            return "teacher";
        } else {
            map.put("notice", "请输入正确的账号与密码！");
            map.put("failname", String.valueOf(teacher.getLoginId()));
            return "teacherlogin";
        }
    }
    @GetMapping("/courseList4Teacher")
    public String courseList(HttpSession httpSession, Model model) {
        Integer tid = (Integer) httpSession.getAttribute("teaId");
        model.addAttribute("courseList", teacherService.getCourseListByTid(tid));
        return "courseList4Teacher";
    }

    @GetMapping("/deleteTeach/{cid}")
    public String deleteTeach(@PathVariable("cid") Integer cid, HttpSession session) {
        teacherService.deleteTeach((Integer) session.getAttribute("teaId"), cid);
        return "redirect:/courseList";

    }

    @PostMapping("/doModifyTeacherPassword")
    public String doModifyPassword(Long loginId, String oldpwd, String password, HttpSession httpSession, Map<String, String> map) throws NoSuchAlgorithmException {
        Teacher teacher = teacherService.getTeacherByLoginId(loginId);
        map.put("name", teacher.getName());
        map.put("loginId", teacher.getLoginId().toString());
        if (teacherService.canModifyPassword(loginId, oldpwd, password)) {
            httpSession.setAttribute("modified", "succeeded");
        } else {
            httpSession.setAttribute("modified", "failed");
        }
        return "teacher";
    }

    @GetMapping("/tealogout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("teaId");
        httpSession.removeAttribute("warnings");
        return "teacherlogin";
    }
}
