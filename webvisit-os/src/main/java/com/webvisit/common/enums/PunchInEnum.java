package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

public enum  PunchInEnum {

    /**
     * 正常签到
     */
    NORMAL(0, "正常签到"),

    /**
     * 未签到
     */
    MISS(1, "未签到"),

    /**
     * 迟到
     */
    LATE(2,"迟到");

    private Integer code;

    private String msg;

    PunchInEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (PunchInEnum punchInEnum : values()) {
            if (punchInEnum.code.equals(code)) {
                return punchInEnum.msg;
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
