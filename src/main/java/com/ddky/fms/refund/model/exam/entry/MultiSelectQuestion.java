package com.ddky.fms.refund.model.exam.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/***
 * 多道选择题
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultiSelectQuestion extends ExamBean {
    private Long id;
    private Integer subjectId;
    private Integer gradeId;
    private String title;
    private Long creatorId;
    private Integer createTime;
    private Integer updateTime;
    private List<SelectQuestion> selectQuestionList;
}
