package com.ddky.fms.refund.model.students.entry;

import lombok.Data;

import java.util.Date;


/***
 * @author oudi
 */
@Data
public class GradeInfo {
    private Integer gradeId;
    private String name;
    private Integer state;
    private Integer orderNo;
    private Long createTime;
}
