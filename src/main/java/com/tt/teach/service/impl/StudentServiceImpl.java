package com.tt.teach.service.impl;

import com.tt.teach.dao.Studentdao;
import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    //修改学生信息的方法
    @Transactional//增加事务
    public int updateStudent(Student student) {
        return studentdao.updateStudent(student);
    }
    //删除学生信息的方法
    @Transactional//删除也需要增加事务
    public int delectStudent(Integer stuNo) {
        return studentdao.delectStudent(stuNo);
    }

    public Student getStudentByNo(Integer studentNo) {
        return studentdao.getStudentByNo(studentNo);
    }
}
