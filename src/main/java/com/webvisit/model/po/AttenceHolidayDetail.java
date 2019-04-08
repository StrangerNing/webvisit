package com.webvisit.model.po;

public class AttenceHolidayDetail {
    private Long id;

    private Long empId;

    private Integer leaveType;

    private Long leaveId;

    private Integer thisYearLeaveLeft;

    private Integer lastYearLeaveLeft;

    private Integer vacationNumber;

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

    public Integer getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Integer getThisYearLeaveLeft() {
        return thisYearLeaveLeft;
    }

    public void setThisYearLeaveLeft(Integer thisYearLeaveLeft) {
        this.thisYearLeaveLeft = thisYearLeaveLeft;
    }

    public Integer getLastYearLeaveLeft() {
        return lastYearLeaveLeft;
    }

    public void setLastYearLeaveLeft(Integer lastYearLeaveLeft) {
        this.lastYearLeaveLeft = lastYearLeaveLeft;
    }

    public Integer getVacationNumber() {
        return vacationNumber;
    }

    public void setVacationNumber(Integer vacationNumber) {
        this.vacationNumber = vacationNumber;
    }
}
