package com.webvisit.service;

import com.webvisit.model.Student;

/**
 * @author zening.zhu@ucarinc.com
 * @version 1.0
 * @date 2019/3/13
 */
public interface StudentService {
    Student getStudentById(int id);

    boolean addStudent(Student student);
}
