package com.tt.teach.pojo;

import java.io.Serializable;
/**
 *@作者：庞屿芃
 *@时间：2018/12/19 14:31
 *@描述：实体类Subject-->对应数据库的科目表
*/
public class Subject implements Serializable {
    private Integer subjectNo;
    private String subjectName;
    private Integer classHour;
    private Integer gradeID;
}
