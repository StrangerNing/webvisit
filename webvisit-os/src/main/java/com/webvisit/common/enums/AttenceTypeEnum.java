package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

public enum AttenceTypeEnum {
    /**
     * 正常
     */
    NORMAL(0, "正常"),

    /**
     * 年假
     */
    ANNUAL(1, "年假"),

    /**
     * 请假
     */
    LEAVE(2,"请假");

    private Integer code;

    private String msg;

    AttenceTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (AttenceTypeEnum attenceTypeEnum : values()) {
            if (attenceTypeEnum.code.equals(code)) {
                return attenceTypeEnum.msg;
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
