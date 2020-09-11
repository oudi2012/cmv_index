package com.ddky.fms.refund.model.students.vo;

import com.ddky.fms.refund.model.students.entry.TeacherInfo;
import lombok.Data;

import java.util.List;

/***
 * @author a
 */
@Data
public class TeacherInfoListVo {
    private List<TeacherInfo> teacherInfoList;
}
