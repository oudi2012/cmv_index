package com.ddky.fms.refund.model.students.config;

import com.ddky.fms.refund.model.base.vo.KeyValueVo;

import java.util.ArrayList;
import java.util.List;

/***
 * 监护人身份类型
 * @author a
 */
public enum GuardianRoleEnum {
    /** 父亲 */
    FATHER(10, "爸爸"),
    FATHER_01(11, "继父"),
    MATHER(20, "妈妈"),
    MATHER_01(21, "继母"),
    GRANDPA(30, "爷爷"),
    GRANDMA(40, "奶奶"),
    GRANDPA_01(31, "姥爷"),
    GRANDMA_01(41, "姥姥");

    private final int code;
    private final String name;

    GuardianRoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GuardianRoleEnum getByCode(int code) {
        for (GuardianRoleEnum item : GuardianRoleEnum.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public static List<KeyValueVo> listItem() {
        List<KeyValueVo> keyValueVoList = new ArrayList<>();
        for (GuardianRoleEnum item : GuardianRoleEnum.values()) {
            KeyValueVo keyValueVo = new KeyValueVo();
            keyValueVo.setCode(Long.parseLong(String.valueOf(item.code)));
            keyValueVo.setName(item.name);
            keyValueVoList.add(keyValueVo);
        }
        return keyValueVoList;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
