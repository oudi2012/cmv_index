package com.ddky.fms.refund.model.admin.entry;


import com.ddky.fms.refund.model.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author a
 * id, userName, passWord, areaId, headImage, realName
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminInfo extends AbstractUser {

    /** 注册日期 */
    private Long    regDate;
    /** 头像 */
    private String  headImage;
    /** 所属地区 */
    private Integer areaId;
    /** 真实姓名 */
    private String  realName;
    private Long    updateTime;
}
