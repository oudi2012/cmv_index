package com.ddky.fms.refund.model.books.config;


/**
 * 卷册
 * @author a
 */
public enum VolumeEnum {

    /** 上册 */
    NONE(0, "无册"),
    UP(1, "上册"),
    DOWN(2, "下册"),
    ALL(3, "全一册"),
    FIRST(11, "第一册"),
    SECOND(12, "第二册"),
    THIRD(13, "第三册"),
    FORTH(14, "第四册"),
    FIFTH(15, "第五册"),
    SIXTH(16, "第六册"),
    SEVENTH(17, "第七册"),
    EIGHTH(18, "第八册"),
    NINTH(19, "第九册");

    /**状态编码**/
    private int code;

    /**状态标题**/
    private String title;

    /**
     * 构造函数
     * @param code 状态编码
     * @param title 状态标题
     */
    VolumeEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public static int getCodeByTitle(String title) {
        for (VolumeEnum item : VolumeEnum.values()) {
            if (item.title.equals(title)) {
                return item.code;
            }
        }
        return 1;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
