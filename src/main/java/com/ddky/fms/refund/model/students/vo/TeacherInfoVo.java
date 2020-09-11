package com.ddky.fms.refund.model.students.vo;

import com.ddky.fms.refund.model.students.entry.TeacherInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherInfoVo extends TeacherInfo {
    private String schoolName;
    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer townCode;
    private String townName;
}
