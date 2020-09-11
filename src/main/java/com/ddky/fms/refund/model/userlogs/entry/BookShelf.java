package com.ddky.fms.refund.model.userlogs.entry;

import lombok.Data;

import java.util.Date;

/***
 * 我的书架
 * @author a
 */
@Data
public class BookShelf {
    private Long      shelfId;
    private Long      userId;
    private Long      bookId;
    /** 书架类别 */
    private Integer   cateId;
    /** 图书封面 */
    private String    bookCover;
    /** 阅读进度 */
    private Integer   readPercent;
    private Date      createDate;
}
