package com.ddky.fms.refund.model.userlogs.entry;


import lombok.Data;

import java.util.Date;

/***
 * 收藏
 * @author a
 */
@Data
public class Collections {

    private Long        coletId;
    private Long        userId;
    /** 收藏文件夹编号 */
    private Long        folderId;
    /** 收藏信息类型 */
    private Integer     infoType;
    /** 收藏信息编号 */
    private Long        infoId;
    private String      title;
    /** 标志图片 */
    private String      imageIndex;
    /** 内容图片 */
    private String      images;
    /** 标签 */
    private String      tags;
    private Date        createDate;

}
