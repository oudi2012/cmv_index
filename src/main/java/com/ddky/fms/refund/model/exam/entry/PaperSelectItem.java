package com.ddky.fms.refund.model.exam.entry;


import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 * id, examPageId, questionId, title, itemNo, createTime
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaperSelectItem extends ExamBean {
    private Integer id;
    private Integer examPageId;
    private Integer questionId;
    private String title;
    private String itemNo;
    private Integer createTime;
}
