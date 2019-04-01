package com.webvisit.model.po;

import java.util.Date;

public class AttenceAnnul {
    private Long id;

    private Long companyId;

    private Date expireDate;

    private Byte accumulateToNextYear;

    private Byte probationHas;

    private Byte graduationOneYearHas;

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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Byte getAccumulateToNextYear() {
        return accumulateToNextYear;
    }

    public void setAccumulateToNextYear(Byte accumulateToNextYear) {
        this.accumulateToNextYear = accumulateToNextYear;
    }

    public Byte getProbationHas() {
        return probationHas;
    }

    public void setProbationHas(Byte probationHas) {
        this.probationHas = probationHas;
    }

    public Byte getGraduationOneYearHas() {
        return graduationOneYearHas;
    }

    public void setGraduationOneYearHas(Byte graduationOneYearHas) {
        this.graduationOneYearHas = graduationOneYearHas;
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