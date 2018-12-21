package com.tt.teach.dao;

import com.tt.teach.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //mybatis持久层框架的注解
@Repository
public interface Studentdao {
    @Select("select * from student where studentNo=#{studentNo} and loginPwd=#{loginPwd}")
    Student doLogin(Student student);
    //别名要和实体类中的一样
    @Select("SELECT student.*,grade.gradeName AS gradeName FROM student,grade WHERE student.gradeId=grade.gradeID")
    List<Student> getStudentList();
    @Update("update student set loginPwd=#{loginPwd},studentName=#{studentName},phone=#{phone} where studentNo=#{studentNo}")
    int updateStudent(Student student);
    @Delete("delete from student where studentNo=#{stuNo}")
    int delectStudent(Integer stuNo);
    @Select("select studentName from student where studentNo=#{studentNo}")
    Student getStudentByNo(Integer studentNo);
}
