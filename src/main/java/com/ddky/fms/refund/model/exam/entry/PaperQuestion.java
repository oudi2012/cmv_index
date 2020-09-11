package com.ddky.fms.refund.model.exam.entry;


import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 * id, examPageId, questionId, title, answers, hardLevelId, score, minTime, maxTime, accuracy, createTime, updateTime
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaperQuestion extends ExamBean {
    private Integer id;
    private Integer examPageId;
    private Integer questionId;
    private String title;
    private String answers;
    private Integer hardLevelId;
    private Integer score;
    private Integer minTime;
    private Integer maxTime;
    private Integer accuracy;
    private Integer createTime;
    private Integer updateTime;
}
