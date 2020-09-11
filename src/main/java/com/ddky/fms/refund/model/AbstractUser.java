package com.ddky.fms.refund.model;

import lombok.Data;

/***
 * 用户信息
 * @author a
 */
@Data
public class AbstractUser {
    private Long    id;
    private String  phone;
    private String  userName;
    private String  passWord;
}
