package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/22
 */

public enum SortEnum {
    /**
     * 升序
     */
    ASC("ASC"),
    /**
     * 降序
     */
    DESC("DESC");

    private String code;

    SortEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
