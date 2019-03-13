package com.webvisit.controller;

import com.webvisit.model.Student;
import com.webvisit.service.StudentService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zening.zhu@ucarinc.com
 * @version 1.0
 * @date 2019/3/13
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/webvisit")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/showStudent")
    @ResponseBody
    public Student toIndex(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        Student student = this.studentService.getStudentById(userId);
        return student;
    }

    @RequestMapping("/addStudent")
    @ResponseBody
    public Boolean add(HttpServletRequest request){
        Student student = new Student();
        student.setClas(request.getParameter("clas"));
        student.setName(request.getParameter("name"));
        return studentService.addStudent(student);
    }
}
