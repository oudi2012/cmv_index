package com.ddky.fms.refund.model.books.config;

/***
 * 选修和必修
 * @author a
 */
public enum ElectiveEnum {
    /** compulsory 必修 */
    ELECTIVE(0, "选修"),
    COMPULSORY(1, "必修");

    /**状态编码**/
    private int code;

    /**状态标题**/
    private String title;

    /**
     * 构造函数
     * @param code 状态编码
     * @param title 状态标题
     */
    ElectiveEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public static int getCodeByTitle(String title) {
        for (ElectiveEnum item : ElectiveEnum.values()) {
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
