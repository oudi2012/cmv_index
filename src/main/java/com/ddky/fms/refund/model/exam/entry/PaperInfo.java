package com.ddky.fms.refund.model.exam.entry;


import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * id, subjectId, gradeId, title, creatorId, createTime, updateTime, examTime, examLong, expireLong
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaperInfo extends ExamBean {
    private Integer id;
    private String title;
    private Integer subjectId;
    private Integer gradeId;
    private Long creatorId;
    private Integer createTime;
    private Integer updateTime;
    private Integer examTime;
    private Integer examLong;
    private Integer expireLong;
}
