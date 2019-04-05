package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/5
 */
public enum LeaveTypeEnum {
    /**
     * 系统默认请假类型
     */
    DEFAULT(0, "默认"),

    /**
     * 企业自增请假类型
     */
    COMPANY_ADD(1, "企业自增"),

    /**
     * 企业自删请假类型
     */
    COMPANY_DELETE(2,"企业自删");

    private Integer code;

    private String msg;

    LeaveTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (LeaveTypeEnum leaveTypeEnum : values()) {
            if (leaveTypeEnum.code.equals(code)) {
                return leaveTypeEnum.msg;
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
