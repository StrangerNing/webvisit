package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/11
 */

public enum RegulationTypeEnum {

    /**
     * 系统默认
     */
    DEFAULT(0, "默认"),

    /**
     * 企业自增
     */
    COMPANY_ADD(1, "企业自增");

    private Integer code;

    private String msg;

    RegulationTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (RegulationTypeEnum regulationTypeEnum : values()) {
            if (regulationTypeEnum.code.equals(code)) {
                return regulationTypeEnum.msg;
            }
        }
        return "未知的操作类型";
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
