package com.ddky.fms.refund.model.exam.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 * 问答题
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AskQuestion extends ExamBean {
    private Integer id;
    private String title;
    private Integer subjectId;
    private Integer gradeId;
    private String examScope;
    private Long creatorId;
    private String answers;
    private Integer createTime;
    private Integer updateTime;
}
