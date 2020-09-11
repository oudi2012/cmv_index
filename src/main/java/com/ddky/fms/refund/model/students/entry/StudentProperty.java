package com.ddky.fms.refund.model.students.entry;

import lombok.Data;

/***
 * 课外属性
 * @author a
 */
@Data
public class StudentProperty {
    /** 课外属性编号 */
    private Long        propId;
    /** 用户编号 */
    private Long        userId;
    /** 身高 */
    private Integer     bodyHeight;
    /** 体重 */
    private Integer     bodyWeight;
    /** 星座 */
    private Integer     constellation;
    /** 血型 */
    private Integer     bloodType;
    /** 属相 */
    private Integer     zodiac;
}
