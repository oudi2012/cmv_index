package com.ddky.fms.refund.model.base.config;

/***
 * 用户类型
 * @author a
 */
public enum UserTypeEnum {
    /** admin */
    USER_ADMIN("admin", "admin"),
    USER_STUDENT("student", "student"),
    USER_TEACHER("teacher", "teacher"),
    USER_GUARDIAN("guardian", "guardian");

    private final String code;
    private final String name;

    UserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
