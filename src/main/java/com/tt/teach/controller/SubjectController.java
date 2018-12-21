package com.tt.teach.controller;

import com.tt.teach.pojo.Grade;
import com.tt.teach.pojo.Result;
import com.tt.teach.pojo.Subject;
import com.tt.teach.service.SubjectService;
import com.tt.teach.utils.BaseController;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sub")
public class SubjectController extends BaseController {
    @Resource
    private SubjectService subjectService;
    //成绩页面的查询
    @RequestMapping("/subject")
    public String subject(Model model) {
        model.addAttribute("gradeList",subjectService.getGrade());
        model.addAttribute("subjectList",subjectService.getSubjectList());
        return "/subject/subject";
    }
    //查询
    @RequestMapping(value = "/getSubjectList",method = RequestMethod.GET)
    @ResponseBody
    public Object getSubjectList() {
        List<Subject> list = subjectService.getSubjectList();
        return list;
    }
    @RequestMapping(value = "/deleteSubject/{subjectNo}",method =RequestMethod.DELETE)
    @ResponseBody
    public Object deleteSubject(@PathVariable Integer subjectNo){
        int result = subjectService.deleteSubject(subjectNo);
        if (result>0){
            return JsonResult.ok("删除成功",result);
        }
        return JsonResult.no("删除失败",result);
    }

    @RequestMapping(value = "/updateSubject",method =RequestMethod.PUT)
    @ResponseBody
    public Object updateSubject(@RequestParam Integer subjectNo,@RequestParam Integer classHour){
        Subject subject = new Subject();
        subject.setClassHour(classHour);
        subject.setSubjectNo(subjectNo);
        int result = subjectService.updateSubject(subject);
        if (result>0){
            return JsonResult.ok("修改成功",result);
        }
        return JsonResult.no("修改失败",result);
    }

    @RequestMapping(value = "/addSubject",method =RequestMethod.PUT)
    @ResponseBody
    public Object addSubject(@RequestParam Integer gradeID,@RequestParam Integer classHour,@RequestParam String subjectName){
        Subject subject = new Subject();
        subject.setClassHour(classHour);
        subject.setGradeID(gradeID);
        subject.setSubjectName(subjectName);
        int result = subjectService.addSubject(subject);
        if (result>0){
            return JsonResult.ok("添加成功",result);
        }
        return JsonResult.no("添加失败",result);
    }
}
