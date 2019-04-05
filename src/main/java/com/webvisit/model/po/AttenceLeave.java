package com.webvisit.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class AttenceLeave {
    private Long id;

    private Long companyId;

    private String name;

    private Integer type;

    private Integer availableDays;

    private BigDecimal salaryPercent;

    private Date createTime;

    private Long createAccountId;

    private Date modifyTime;

    private Long modifyAccountId;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(Integer availableDays) {
        this.availableDays = availableDays;
    }

    public BigDecimal getSalaryPercent() {
        return salaryPercent;
    }

    public void setSalaryPercent(BigDecimal salaryPercent) {
        this.salaryPercent = salaryPercent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(Long createAccountId) {
        this.createAccountId = createAccountId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyAccountId() {
        return modifyAccountId;
    }

    public void setModifyAccountId(Long modifyAccountId) {
        this.modifyAccountId = modifyAccountId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
