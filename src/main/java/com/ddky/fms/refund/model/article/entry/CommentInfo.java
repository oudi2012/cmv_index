package com.ddky.fms.refund.model.article.entry;

import lombok.Data;

/***
 * 评论
 * @author a
 */
@Data
public class CommentInfo {
    private Long id;
    private Long userId;
    /** 被评论编号 */
    private Long rePlayId;
    /** 10:原文评论 20:回复评论 */
    private int type;
    /** 评论内容 */
    private String content;
}
