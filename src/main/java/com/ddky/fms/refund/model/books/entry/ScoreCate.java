package com.ddky.fms.refund.model.books.entry;

import lombok.Data;

/**
 * 成绩类型
 * @Author wanghaikuo
 * @Date 2020/6/26
 **/
@Data
public class ScoreCate {
    private Integer     cateId;
    /** 成绩类型 */
    private Integer     type;
    /** 成绩名称 */
    private String      name;
    /** 成绩值 */
    private Integer     score;
    /** 成绩描述 */
    private String      scoreDesc;
    private Integer     orderNo;
}
