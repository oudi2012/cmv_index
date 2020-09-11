package com.ddky.fms.refund.model.students.vo;

import com.ddky.fms.refund.model.students.entry.StudentInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentInfoVo extends StudentInfo {
    private Integer province;
    private Integer city;
    private Integer town;
}
