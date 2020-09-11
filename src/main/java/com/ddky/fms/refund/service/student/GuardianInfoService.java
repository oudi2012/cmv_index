package com.ddky.fms.refund.service.student;

import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import com.ddky.fms.refund.model.students.vo.GuardianStudentVo;

/***
 * 监护人
 * @author a
 */
public interface GuardianInfoService extends UserInfoService<GuardianInfo>{

    /***
     * 绑定监护人
     * @param guardianStudentVo guardianStudentVo
     * @return
     */
    int bindGuardianInfo(GuardianStudentVo guardianStudentVo);

}
