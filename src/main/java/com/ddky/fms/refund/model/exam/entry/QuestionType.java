package com.ddky.fms.refund.model.exam.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 试题类型
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionType extends ExamBean {
    private Integer id;
    private String title;
    private Integer state;
    private Integer orderNo;
    private Integer createTime;
    private Integer updateTime;
}
