package com.webvisit.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class AttenceRegulation {
    private Long id;

    private Long companyId;

    private String name;

    private Date punchInStart;

    private Date punchInEnd;

    private Date punchOutStart;

    private Date punchOutEnd;

    private Date allowLate;

    private Date allowLeaveEarly;

    private Integer allowLocationOffset;

    private BigDecimal checkLocationLon;

    private BigDecimal checkLocationLat;

    private Integer type;

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

    public Date getPunchInStart() {
        return punchInStart;
    }

    public void setPunchInStart(Date punchInStart) {
        this.punchInStart = punchInStart;
    }

    public Date getPunchInEnd() {
        return punchInEnd;
    }

    public void setPunchInEnd(Date punchInEnd) {
        this.punchInEnd = punchInEnd;
    }

    public Date getPunchOutStart() {
        return punchOutStart;
    }

    public void setPunchOutStart(Date punchOutStart) {
        this.punchOutStart = punchOutStart;
    }

    public Date getPunchOutEnd() {
        return punchOutEnd;
    }

    public void setPunchOutEnd(Date punchOutEnd) {
        this.punchOutEnd = punchOutEnd;
    }

    public Date getAllowLate() {
        return allowLate;
    }

    public void setAllowLate(Date allowLate) {
        this.allowLate = allowLate;
    }

    public Date getAllowLeaveEarly() {
        return allowLeaveEarly;
    }

    public void setAllowLeaveEarly(Date allowLeaveEarly) {
        this.allowLeaveEarly = allowLeaveEarly;
    }

    public Integer getAllowLocationOffset() {
        return allowLocationOffset;
    }

    public void setAllowLocationOffset(Integer allowLocationOffset) {
        this.allowLocationOffset = allowLocationOffset;
    }

    public BigDecimal getCheckLocationLon() {
        return checkLocationLon;
    }

    public void setCheckLocationLon(BigDecimal checkLocationLon) {
        this.checkLocationLon = checkLocationLon;
    }

    public BigDecimal getCheckLocationLat() {
        return checkLocationLat;
    }

    public void setCheckLocationLat(BigDecimal checkLocationLat) {
        this.checkLocationLat = checkLocationLat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
