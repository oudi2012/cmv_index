package com.ddky.fms.refund.model.userlogs.entry;

import lombok.Data;

import java.util.Date;

/***
 * 家庭作业
 * @author a
 */
@Data
public class HomeWork {
    /** 编号 */
    private Long        homeWorkId;
    private Long        userId;
    /** 学校编号 */
    private Long        schoolId;
    private Integer     gradeId;
    private Integer     classId;
    /** 科目 */
    private Integer     subjectId;
    /** 作业内容 */
    private String      workContent;
    /** 作业图片-代表图 */
    private String      indexImage;
    /** 创建时间 */
    private Date        createDate;
    /** 有效-开始时间 */
    private Long        beginDate;
    /** 有效-结束时间 */
    private Long        endDate;

}
