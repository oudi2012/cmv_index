package com.ddky.fms.refund.model.exam.entry;


import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaperQuestionType extends ExamBean {
    private Integer id;
    private Integer examPageId;
    private Integer questionTypeId;
    private Integer createTime;
    private Integer updateTime;
}
