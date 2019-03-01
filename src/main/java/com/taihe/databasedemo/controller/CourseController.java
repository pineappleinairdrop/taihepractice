package com.taihe.databasedemo.controller;

import com.taihe.databasedemo.service.CourseService;
import com.taihe.databasedemo.service.StudentService;
import com.taihe.databasedemo.service.TeacherService;
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
    @Autowired
    TeacherService teacherService;

    @GetMapping("/selectCourse")
    public String courseList(Model model, HttpSession session) {
        model.addAttribute("courseList", courseService.courseList4Student((Integer) session.getAttribute("userId")));
        return "selectCourse";
    }

    @GetMapping("/selectCourse4Teacher")
    public String courseList4Teacher(Model model, HttpSession session) {
        model.addAttribute("courseList", courseService.courseList4Student((Integer) session.getAttribute("userId")));
        return "selectCourse4Teacher";
    }

    @PostMapping("/selectCoursePost")
    @ResponseBody
    public void selectCourse(HttpSession session, Integer cid) {
        Integer sid = (Integer) session.getAttribute("stuId");
        studentService.addTake(sid, cid);
    }

    @PostMapping("/selectCoursePost4Teacher")
    @ResponseBody
    public void selectCourse4Teacher(HttpSession httpSession, Integer cid) {
        Integer tid = (Integer) httpSession.getAttribute("teaId");
        teacherService.addTeach(tid, cid);

    }

    @PostMapping("/courseCount")
    @ResponseBody
    public String courseCount(Integer cid){
        System.out.println(cid);
        return String.valueOf(cid);
    }
}
