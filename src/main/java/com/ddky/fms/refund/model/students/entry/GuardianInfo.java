package com.ddky.fms.refund.model.students.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 监护人
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GuardianInfo extends UserInfo {
    /** 身份 GuardianRoleEnum  */
    private Integer roleId;
}
