package com.taihe.databasedemo.controller;

import com.taihe.databasedemo.service.CourseService;
import com.taihe.databasedemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;

    @GetMapping("/selectCourse")
    public String courseList(Model model, HttpSession session) {
        model.addAttribute("courseList", courseService.courseList((Integer) session.getAttribute("userId")));
        return "selectCourse";
    }

    @PostMapping("/selectCoursePost")
    @ResponseBody
    public void selectCourse(HttpSession session, Integer cid) {
        Integer sid = (Integer) session.getAttribute("userId");
        studentService.addTake(sid, cid);
    }

}
