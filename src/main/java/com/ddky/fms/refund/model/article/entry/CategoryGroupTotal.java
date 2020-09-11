package com.ddky.fms.refund.model.article.entry;

import lombok.Data;

/***
 * 分类下的文章条数
 * @author a
 */
@Data
public class CategoryGroupTotal {
    private Long id;
    private Long cateId;
    /** 类型编号 */
    private Integer typeId;
    /** 条数 */
    private Integer total;
    /** 开始时间 */
    private Long startDate;
    /** 结束时间 **/
    private Long endDate;
}
