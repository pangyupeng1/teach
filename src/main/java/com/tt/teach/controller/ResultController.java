package com.tt.teach.controller;

import com.tt.teach.pojo.Result;
import com.tt.teach.pojo.Subject;
import com.tt.teach.service.ResultService;
import com.tt.teach.utils.BaseController;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/res")
public class ResultController extends BaseController {
    @Resource
    private ResultService resultService;

    //成绩查询页面的请求
    @RequestMapping("/result")
    public String result(Model model) {
        List<Subject> list = resultService.getSubject();
        model.addAttribute("subjectlist",list);
        return "result/result";
    }
    //成绩查询的接口
    @RequestMapping(value = "/getResultList",method = RequestMethod.GET)
    @ResponseBody
    public Object getResultList() {
        List<Result> list = resultService.getResultList();
        return list;
    }
    //删除成绩的接口
    @RequestMapping(value = "/deleteResult/{resultNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteResult(@PathVariable Integer resultNo) {
        int result = resultService.deleteResult(resultNo);
        if (result>0){
            return JsonResult.ok("删除成功",result);
        }
        return JsonResult.no("删除失败",result);
    }
    //修改成绩的接口
    @RequestMapping(value = "/updateResult",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateResult(@RequestParam Integer resultNo,@RequestParam Integer studentResult) {
        Result result = new Result();
        result.setResultNo(resultNo);
        result.setStudentResult(studentResult);
        int result1 = resultService.updateResult(result);
        if (result1>0){
            return JsonResult.ok("修改成功",result1);
        }
        return JsonResult.no("修改失败",result1);
    }

    @RequestMapping(value = "/addResult",method = RequestMethod.PUT)
    @ResponseBody
    public Object addResult (@RequestParam Integer subjectNo,@RequestParam Integer studentNo,@RequestParam String examDate,@RequestParam Integer studentResult) throws ParseException {
        Result result = new Result();
        result.setSubjectNo(subjectNo);
        result.setStudentResult(studentResult);
        result.setStudentNo(studentNo);
        //因为前段传参的时候时间是string类型的，所以需要使用SimpleDateFormat转换为时间类型的
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.setExamDate(simpleDateFormat.parse(examDate));
        int result2=resultService.addResult(result);
        if (result2>0){
            return JsonResult.ok("添加成功",result2);
        }
        return JsonResult.no("添加失败",result2);
    }


}
