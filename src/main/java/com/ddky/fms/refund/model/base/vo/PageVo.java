package com.ddky.fms.refund.model.base.vo;

import lombok.Data;

/***
 * 分页Vo
 * @author a
 */
@Data
public class PageVo {

    /***
     * 页码
     */
    private int pageIndex = 1;

    /***
     * 条数
     */
    private int pageSize = 10;

}
