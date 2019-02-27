package com.taihe.databasedemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.taihe.databasedemo.entity.Course;
import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.service.CourseService;
import com.taihe.databasedemo.service.StudentService;
import com.taihe.databasedemo.utils.DataTablePageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @GetMapping("/admin")
    public String adminHome() {
        return "admin";
    }



    @GetMapping("/courseList4Admin")
    public String courseList4Admin(Model model) {
        model.addAttribute("courseList", courseService.courseList());
        return "courseList4Admin";
    }

    @GetMapping("/deleteCourse/{cid}")
    @ResponseBody
    public void deleteCourse(@PathVariable("cid") Integer cid){
        courseService.deleteCourseById(cid);
    }

    @PostMapping("/doModifyCourse")
    @ResponseBody
    public void doModifyCourse(Course course){
        System.out.println(course.toString());
        courseService.modifyCourse(course);
    }
    @PostMapping("/refreshStudentList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String refreshStudentList(HttpSession httpSession, HttpServletRequest request) {

        DataTablePageUtil<Student> dataTable = new DataTablePageUtil<>(request);

        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        List<Student> studentList = studentService.getStudentList();

        PageInfo<Student> studentPageInfo = new PageInfo<>(studentList);

        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(studentPageInfo.getList());
        dataTable.setRecordsTotal((int) studentPageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        return new Gson().toJson(dataTable);
    }
}
