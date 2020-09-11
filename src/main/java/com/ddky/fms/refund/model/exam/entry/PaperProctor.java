package com.ddky.fms.refund.model.exam.entry;


import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 * id, examPageId, userId, state, createTime
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaperProctor extends ExamBean {
    private Integer id;
    private Integer examPageId;
    private Long userId;
    private Integer state;
    private Integer createTime;
}
