package com.ddky.fms.refund.model.article.entry;

import lombok.Data;

import java.util.Date;

/***
 * 文章
 * @author a
 */
@Data
public class ArticleInfo {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private Long cateId;
    private String tags;
    private Date createDate;
    /** 排序编号 */
    private Integer orderNo;
    /** 收藏次数 */
    private Integer totalCollect;
    /** 点赞次数 */
    private Integer totalZan;
    /** 评论条数 */
    private Integer totalComment;
}
