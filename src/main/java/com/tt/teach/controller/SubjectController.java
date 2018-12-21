package com.tt.teach.controller;

import com.tt.teach.service.SubjectService;
import com.tt.teach.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sub")
public class SubjectController extends BaseController {
    @Resource
    private SubjectService subjectService;
    //成绩页面的查询
    @RequestMapping("/subject")
    public String subject() {
        return "/subject/subject";
    }
}
