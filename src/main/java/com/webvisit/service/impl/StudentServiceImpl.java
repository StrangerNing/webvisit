package com.webvisit.service.impl;

import com.webvisit.dao.StudentMapper;
import com.webvisit.model.Student;
import com.webvisit.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zening.zhu@ucarinc.com
 * @version 1.0
 * @date 2019/3/13
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentDao;

    @Override
    public Student getStudentById(int id) {
        return studentDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean addStudent(Student student) {
        boolean result = false;
        try {
            studentDao.insertSelective(student);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
