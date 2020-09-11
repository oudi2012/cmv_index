package com.ddky.fms.refund.model.userlogs.entry;

import lombok.Data;

import java.util.Date;

/**
 * 读书笔记
 * @author a
 */
@Data
public class ReadNote {
    private Long        id;
    /** 图书编号 */
    private Long        bookId;
    /** 页码 */
    private Integer     pageNo;
    /** 行号 */
    private Integer     lineNo;
    /** 操作人 */
    private Long        userId;
    /** 10:添加 20 修改 30：删除 */
    private Integer     type;
    private String      content;
    /** 笔记时间 */
    private Date        createDate;
}
