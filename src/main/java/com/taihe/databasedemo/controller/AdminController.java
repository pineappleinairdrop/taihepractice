package com.taihe.databasedemo.controller;

import com.google.gson.Gson;
import com.taihe.databasedemo.entity.Course;
import com.taihe.databasedemo.entity.Student;
import com.taihe.databasedemo.entity.Teacher;
import com.taihe.databasedemo.service.CourseService;
import com.taihe.databasedemo.service.StudentService;
import com.taihe.databasedemo.service.TeacherService;
import com.taihe.databasedemo.utils.DataTablePageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    TeacherService teacherService;


    @GetMapping("/courseList4Admin")
    public String courseList4Admin(Model model) {
        model.addAttribute("courseList", courseService.courseList());
        return "courseList4Admin";
    }

    @GetMapping("/deleteCourse/{cid}")
    @ResponseBody
    public void deleteCourse(@PathVariable("cid") Integer cid) {
        courseService.deleteCourseById(cid);
    }

    @PostMapping("/doModifyCourse")
    @ResponseBody
    public void doModifyCourse(Course course) {
        System.out.println(course.toString());
        courseService.modifyCourse(course);
    }

    @PostMapping("/refreshStudentList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String refreshStudentList(/*HttpSession httpSession,*/ HttpServletRequest request) {

        DataTablePageUtil<Student> dataTable = new DataTablePageUtil<>(request);
        //PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        int start = (dataTable.getPage_num() - 1) * dataTable.getPage_size();
        int end = start + dataTable.getPage_size();
        List<Student> studentList = studentService.getStudentList(start, end);
        //PageInfo<Student> studentPageInfo = new PageInfo<>(studentList);

        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(studentList);//studentPageInfo.getList()
        dataTable.setRecordsTotal(studentService.maxId() / 10 + 1);
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        return new Gson().toJson(dataTable);
    }

    @PostMapping("/addCourse")
    public String addCourse(Course course) {
        courseService.addCourse(course);
        return "newCourse";
    }

    @PostMapping("/addStudent")
    public String addStudent(String name, String sex, String registerDate, Double tuition) {
        Student student = new Student(name, sex, registerDate, tuition);
        studentService.addStudent(student);
        return "newStudent";
    }

    @PostMapping("/refreshTeacherList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String refreshTeacherList(/*HttpSession httpSession,*/ HttpServletRequest request) {

        DataTablePageUtil<Teacher> dataTable = new DataTablePageUtil<>(request);

        int start = (dataTable.getPage_num() - 1) * dataTable.getPage_size();
        int end = start + dataTable.getPage_size();

        List<Teacher> teacherList = teacherService.getTeacherList(start, end);

        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(teacherList);//studentPageInfo.getList()
        dataTable.setRecordsTotal(studentService.maxId() / 100 + 1);
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        return new Gson().toJson(dataTable);
    }

    @Value("${adminpassword}")
    String adminpassword;

    @PostMapping("/adminloginAction")
    public String adminlogin(String password, Model model,HttpSession httpSession) {
        if (password.equals(adminpassword)) {
            httpSession.setAttribute("admin", Boolean.TRUE);
            return "admin";
        } else {
            model.addAttribute("notice", "请输入正确的密码！");
            return "adminlogin";
        }
    }
    @GetMapping("/adminlogout")
    public String adminLogout(HttpSession httpSession){
        httpSession.removeAttribute("admin");
        return "adminlogin";
    }
    @PostMapping("/addTeacher")
    public String addTeacher(String name) {
        teacherService.addTeacher(name);
        return "newTeacher";
    }
}
