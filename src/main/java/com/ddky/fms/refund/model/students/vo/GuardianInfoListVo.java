package com.ddky.fms.refund.model.students.vo;

import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import lombok.Data;

import java.util.List;

/***
 * @author a
 */
@Data
public class GuardianInfoListVo {
    private List<GuardianInfo> guardianInfoList;
}
