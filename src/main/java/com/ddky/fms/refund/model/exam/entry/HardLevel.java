package com.ddky.fms.refund.model.exam.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 难度系数
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HardLevel extends ExamBean {
    private Integer id;
    private String title;
    private Integer state;
    private Integer orderNo;
    private Integer createTime;
    private Integer updateTime;
}
