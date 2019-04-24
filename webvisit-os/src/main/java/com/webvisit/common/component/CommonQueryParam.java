package com.webvisit.common.component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webvisit.common.enums.SortEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/22
 */

@Data
public class CommonQueryParam implements Serializable {
    /**
     * 分页大小，默认10
     */
    private Integer pageSize = 10;

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 开始记录
     */
    private Integer start;

    /**
     * 开始时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date beginTime;

    /**
     * 截止时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date endTime;


    /**
     * 排序方式 默认降序
     * @see SortEnum
     */
    private String sortBy = SortEnum.DESC.getCode();

    public Integer getPageNum() {
        if (this.pageNum == null || this.pageNum < 1) {
            this.pageNum = 1;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = 10;
        }
        return pageSize;
    }

    public Integer getStart() {
        return (this.getPageNum() - 1) * getPageSize();
    }
}
