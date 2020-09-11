package com.ddky.fms.refund.model.base.vo;

import lombok.Data;

/**
 * @author a
 */
@Data
public class LoginVo {
    private Long id;
    private String userType;
    private String phone;
    private String userName;
    private String passWord;
    private String token;
}
