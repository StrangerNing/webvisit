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
    LEGAL_HOLIDAY(new Byte("0"), "法定节假日"),

    /**
     * 调休补班
     */
    DUTY_DAY(new Byte("1"), "补班");

    private Byte code;

    private String msg;

    DefaultHolidayTypeEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Byte code) {
        for (DefaultHolidayTypeEnum defaultHolidayTypeEnum : values()) {
            if (defaultHolidayTypeEnum.code.equals(code)) {
                return defaultHolidayTypeEnum.msg;
            }
        }
        return "未知的操作类型";
    }

    public Byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
