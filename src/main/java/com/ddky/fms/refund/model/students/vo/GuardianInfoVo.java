package com.ddky.fms.refund.model.students.vo;

import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GuardianInfoVo extends GuardianInfo {
    private String roleName;
    private Integer province;
    private Integer city;
    private Integer town;
}
