package com.ddky.fms.refund.model.userlogs.entry;

import lombok.Data;

import java.util.Date;

/***
 * 学生完成的作业
 * @author a
 */
@Data
public class StudentWork {
    /** 作业编号 */
    private Long        workId;
    private Long        userId;
    /** 学校编号 */
    private Long        schoolId;
    private Long        gradeId;
    private Long        classId;
    /** 科目编号 */
    private Integer     subjectId;
    private Long        teacherId;
    private Integer     version;
    /** 状态 10:未完成, 20:待批改 30:待确定 40:已确定*/
    private Integer     state;
    /** 成绩类型 */
    private Integer     scoreCateId;
    /** 成绩 */
    private Integer     score;
    /** 成绩描述 */
    private String      scoreDesc;
    /** 作业主图 */
    private String      imageIndex;
    private Date        createDate;
}
