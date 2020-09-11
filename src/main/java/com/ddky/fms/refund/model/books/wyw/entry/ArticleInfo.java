package com.ddky.fms.refund.model.books.wyw.entry;

import lombok.Data;

import java.util.Date;

/***
 * @author oudi
 */
@Data
public class ArticleInfo {
    private Long id;
    private String author;
    private int grade;
    private int dynasty;
    private int cateId;
    private int styleId;
    private String tags;
    private String title;
    private String cont;
    private Date createDate;
    private String href;
}
