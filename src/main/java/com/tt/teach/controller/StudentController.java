package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import com.tt.teach.utils.BaseController;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController extends BaseController {
    @Resource
    private StudentService studentService;
    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "/student/login";
    }
    //系统界面
    @RequestMapping("/index")
    public String index() {
        String studentName = (String) getSession().getAttribute(SESSION_KEY);
        if (studentName!=null){
            return "/student/index";
        }
        return REDIRECT+ "/stu/login";//重定向到登录
    }
    //登录的方法
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin() {
        String xuehao = getRequest().getParameter("studentNo");
        Integer studentNo = Integer.parseInt(xuehao);
        String loginPwd = getRequest().getParameter("loginPwd");
        Student student = new Student();
        student.setLoginPwd(loginPwd);
        student.setStudentNo(studentNo);
        Student student1 = studentService.doLogin(student);
        if (student1!=null){
            //${session.studentName}  使用方法
            getSession().setAttribute(SESSION_KEY,student1.getStudentName());
            return FORWARD+"/stu/index";//转发到index页面
        }else {
            return REDIRECT+"/stu/login";//重定向到登录页面
        }
    }
    //注销的方法
    @RequestMapping("/logout")
    public String logout() {
        getSession().removeAttribute(SESSION_KEY);
        return REDIRECT+"/stu/login";//重定向到登录页面
    }
    //学生查询页面的请求
    @RequestMapping("/student")
    public String student() {
        return "/student/student";
    }
    //学生查询的方法
    @RequestMapping(value = "/getStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Object getStudentList() {
        List<Student> list = studentService.getStudentList();
        return list;
    }
    //学生信息的删除
    @RequestMapping(value = "/delectStudent/{stuNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delectStudent(@PathVariable Integer stuNo) {
        int result = studentService.delectStudent(stuNo);
        if (result>0){
            return JsonResult.ok("删除成功",result);
        }
        return JsonResult.ok("删除失败",result);
    }
    //学生信息的修改
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    public String updateStudent() {
        //学号，姓名，密码，电话
        String xuehao = getRequest().getParameter("stuNo");
        Integer stuNo = Integer.parseInt(xuehao);//强转为integer类型的
        String stuName = getRequest().getParameter("stuName");
        String stuPwd = getRequest().getParameter("stuPwd");
        String stuPhone = getRequest().getParameter("stuPhone");
        Student student = new Student();
        student.setStudentNo(stuNo);
        student.setStudentName(stuName);
        student.setLoginPwd(stuPwd);
        student.setPhone(stuPhone);
        int result = studentService.updateStudent(student);
        if (result>0){
            return FORWARD+"/stu/student";
        }
        return FORWARD+"/stu/student";
    }
}
