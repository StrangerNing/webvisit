package com.webvisit.model.po;

public class AttenceReport {
    private Long id;

    private Long empId;

    private Integer punchInCount;

    private Integer punchInLateCount;

    private Integer punchInMissCount;

    private Integer punchOutCountin;

    private Integer punchOutEarlyCount;

    private Integer punchOutMissCount;

    private Integer askLeaveCount;

    private Integer workOutsideCount;

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

    public Integer getPunchInCount() {
        return punchInCount;
    }

    public void setPunchInCount(Integer punchInCount) {
        this.punchInCount = punchInCount;
    }

    public Integer getPunchInLateCount() {
        return punchInLateCount;
    }

    public void setPunchInLateCount(Integer punchInLateCount) {
        this.punchInLateCount = punchInLateCount;
    }

    public Integer getPunchInMissCount() {
        return punchInMissCount;
    }

    public void setPunchInMissCount(Integer punchInMissCount) {
        this.punchInMissCount = punchInMissCount;
    }

    public Integer getPunchOutCountin() {
        return punchOutCountin;
    }

    public void setPunchOutCountin(Integer punchOutCountin) {
        this.punchOutCountin = punchOutCountin;
    }

    public Integer getPunchOutEarlyCount() {
        return punchOutEarlyCount;
    }

    public void setPunchOutEarlyCount(Integer punchOutEarlyCount) {
        this.punchOutEarlyCount = punchOutEarlyCount;
    }

    public Integer getPunchOutMissCount() {
        return punchOutMissCount;
    }

    public void setPunchOutMissCount(Integer punchOutMissCount) {
        this.punchOutMissCount = punchOutMissCount;
    }

    public Integer getAskLeaveCount() {
        return askLeaveCount;
    }

    public void setAskLeaveCount(Integer askLeaveCount) {
        this.askLeaveCount = askLeaveCount;
    }

    public Integer getWorkOutsideCount() {
        return workOutsideCount;
    }

    public void setWorkOutsideCount(Integer workOutsideCount) {
        this.workOutsideCount = workOutsideCount;
    }
}