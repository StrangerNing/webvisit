package com.webvisit.model.po;

import java.util.Date;

public class SecurityInfo {
    private Long id;

    private String operateModule;

    private String operateEvent;

    private Long operateId;

    private String operateName;

    private String operateDetail;

    private Date operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule == null ? null : operateModule.trim();
    }

    public String getOperateEvent() {
        return operateEvent;
    }

    public void setOperateEvent(String operateEvent) {
        this.operateEvent = operateEvent == null ? null : operateEvent.trim();
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public String getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(String operateDetail) {
        this.operateDetail = operateDetail == null ? null : operateDetail.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}