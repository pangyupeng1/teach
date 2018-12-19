package com.tt.teach.service.impl;

import com.tt.teach.dao.Studentdao;
import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private Studentdao studentdao;
    //登录的方法
    public Student doLogin(Student student) {
        return studentdao.doLogin(student);
    }
    //查询学生的方法
    public List<Student> getStudentList() {
        return studentdao.getStudentList();
    }
}
