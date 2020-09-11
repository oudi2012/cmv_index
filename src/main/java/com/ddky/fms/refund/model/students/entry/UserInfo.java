package com.ddky.fms.refund.model.students.entry;


import com.ddky.fms.refund.model.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基本信息
 * @author oudi
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfo extends AbstractUser {
	/** 注册类型 */
	private Integer  regType;
	/** 注册日期 */
	private Long    regDate;
	/** 头像 */
	private String  headImage;
	/** 所属地区 */
	private Integer areaId;
	/** 性别 */
	private Integer sex;
	private String  birthDay;
	/** 真实姓名 */
	private String  realName;
	private Long    updateTime;
}
