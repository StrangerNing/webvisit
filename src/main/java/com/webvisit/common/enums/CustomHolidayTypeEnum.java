package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/5
 */
public enum CustomHolidayTypeEnum {

    NEW(new Byte("0"), "新增"),

    CANCEL(new Byte("1"), "取消");

    private Byte code;

    private String msg;

    CustomHolidayTypeEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Byte code) {
        for (CustomHolidayTypeEnum customHolidayTypeEnum : values()) {
            if (customHolidayTypeEnum.code.equals(code)) {
                return customHolidayTypeEnum.msg;
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
