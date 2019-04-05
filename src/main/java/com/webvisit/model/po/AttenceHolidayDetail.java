package com.webvisit.model.po;

public class AttenceHolidayDetail {
    private Long id;

    private Long empId;

    private Long leaveType;

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

    public Long getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(Long leaveType) {
        this.leaveType = leaveType;
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
