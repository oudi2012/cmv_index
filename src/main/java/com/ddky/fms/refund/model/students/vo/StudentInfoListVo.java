package com.ddky.fms.refund.model.students.vo;

import com.ddky.fms.refund.model.students.entry.StudentInfo;
import lombok.Data;

import java.util.List;

/***
 * @author a
 */
@Data
public class StudentInfoListVo {
    private List<StudentInfo> studentInfoList;
}
