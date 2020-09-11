package com.ddky.fms.refund.model.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * code, name, title
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CntItemVo {
    private int code;
    private String name;
    private String title;
}
