package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

public enum  PunchOutEnum {
    /**
     * 正常签退
     */
    NORMAL(0, "正常签退"),

    /**
     * 未签退
     */
    MISS(1, "未签退"),

    /**
     * 早退
     */
    EARLY(2,"早退");

    private Integer code;

    private String msg;

    PunchOutEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (PunchOutEnum punchOutEnum : values()) {
            if (punchOutEnum.code.equals(code)) {
                return punchOutEnum.msg;
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
