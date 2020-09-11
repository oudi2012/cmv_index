package com.ddky.fms.refund.model.students.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * StudentInfo
 * @author a
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentInfo extends UserInfo{
    /** 监护人编号 */
    private Long guardianId;
    /** 所属学校 */
    private Integer schoolId;
    private Integer gradeId;
    private Integer classId;
    /** 所在排 */
    private Integer siteRow;
    /** 所在列 */
    private Integer siteCol;
}
