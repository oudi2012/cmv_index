package com.ddky.fms.refund.model.students.vo;


import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import lombok.Data;

/***
 * @author a
 */
@Data
public class GuardianStudentVo {
    private GuardianInfo guardianInfo;
    private StudentInfo studentInfo;
}
