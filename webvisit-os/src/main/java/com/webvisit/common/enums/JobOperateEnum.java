package com.webvisit.common.enums;

import java.io.Serializable;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

public enum JobOperateEnum {

    /**
     * 启动
     */
    START(1,"启动"),

    /**
     * 暂停
     */
    PAUSE(2,"暂停"),

    /**
     * 删除
     */
    DELETE(3,"删除");

    private final Integer value;

    private final String desc;

    JobOperateEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getEnumName() {
        return name();
    }
}
