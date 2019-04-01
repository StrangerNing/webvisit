package com.webvisit.model;

import java.math.BigDecimal;
import java.util.Date;

public class PunchDetail {
    private Long id;

    private Long empId;

    private Date punchTime;

    private Date punchInTime;

    private Date punchOutTime;

    private Byte punchInStatus;

    private Byte punchOutStatus;

    private BigDecimal punchInLocationLon;

    private BigDecimal punchInLocationLat;

    private BigDecimal punchOutLocationLon;

    private BigDecimal punchOutLocationLat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public Date getPunchInTime() {
        return punchInTime;
    }

    public void setPunchInTime(Date punchInTime) {
        this.punchInTime = punchInTime;
    }

    public Date getPunchOutTime() {
        return punchOutTime;
    }

    public void setPunchOutTime(Date punchOutTime) {
        this.punchOutTime = punchOutTime;
    }

    public Byte getPunchInStatus() {
        return punchInStatus;
    }

    public void setPunchInStatus(Byte punchInStatus) {
        this.punchInStatus = punchInStatus;
    }

    public Byte getPunchOutStatus() {
        return punchOutStatus;
    }

    public void setPunchOutStatus(Byte punchOutStatus) {
        this.punchOutStatus = punchOutStatus;
    }

    public BigDecimal getPunchInLocationLon() {
        return punchInLocationLon;
    }

    public void setPunchInLocationLon(BigDecimal punchInLocationLon) {
        this.punchInLocationLon = punchInLocationLon;
    }

    public BigDecimal getPunchInLocationLat() {
        return punchInLocationLat;
    }

    public void setPunchInLocationLat(BigDecimal punchInLocationLat) {
        this.punchInLocationLat = punchInLocationLat;
    }

    public BigDecimal getPunchOutLocationLon() {
        return punchOutLocationLon;
    }

    public void setPunchOutLocationLon(BigDecimal punchOutLocationLon) {
        this.punchOutLocationLon = punchOutLocationLon;
    }

    public BigDecimal getPunchOutLocationLat() {
        return punchOutLocationLat;
    }

    public void setPunchOutLocationLat(BigDecimal punchOutLocationLat) {
        this.punchOutLocationLat = punchOutLocationLat;
    }
}