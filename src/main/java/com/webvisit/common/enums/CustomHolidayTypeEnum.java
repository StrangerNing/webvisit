package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/5
 */
public enum CustomHolidayTypeEnum {

    /**
     * 新增节假日
     */
    NEW(0, "新增"),

    /**
     * 取消节假日
     */
    CANCEL(1, "取消");

    private Integer code;

    private String msg;

    CustomHolidayTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (CustomHolidayTypeEnum customHolidayTypeEnum : values()) {
            if (customHolidayTypeEnum.code.equals(code)) {
                return customHolidayTypeEnum.msg;
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
