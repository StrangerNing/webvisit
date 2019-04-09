package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/5
 */
public enum DefaultHolidayTypeEnum {
    /**
     * 法定节假日
     */
    LEGAL_HOLIDAY(0, "法定节假日"),

    /**
     * 调休补班
     */
    DUTY_DAY(1, "补班");

    private Integer code;

    private String msg;

    DefaultHolidayTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (DefaultHolidayTypeEnum defaultHolidayTypeEnum : values()) {
            if (defaultHolidayTypeEnum.code.equals(code)) {
                return defaultHolidayTypeEnum.msg;
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
