package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/3
 */

public enum UserStatusEnum {

    FREEZE(false,"冻结"),

    NORMAL(true,"有效");

    private Boolean code;

    private String msg;

    UserStatusEnum(Boolean code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static String getMsg(Boolean code){
        for (UserStatusEnum userStatusEnum : values()) {
            if (userStatusEnum.code.equals(code)){
                return userStatusEnum.msg;
            }
        }
        return "未知的操作类型";
    }

    public Boolean getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
