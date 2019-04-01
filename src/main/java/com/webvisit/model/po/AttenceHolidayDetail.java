package com.webvisit.model.po;

public class AttenceHolidayDetail {
    private Long id;

    private Long empId;

    private Long leaveType;

    private Byte thisYearLeaveLeft;

    private Byte lastYearLeaveLeft;

    private Byte vacationNumber;

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

    public Byte getThisYearLeaveLeft() {
        return thisYearLeaveLeft;
    }

    public void setThisYearLeaveLeft(Byte thisYearLeaveLeft) {
        this.thisYearLeaveLeft = thisYearLeaveLeft;
    }

    public Byte getLastYearLeaveLeft() {
        return lastYearLeaveLeft;
    }

    public void setLastYearLeaveLeft(Byte lastYearLeaveLeft) {
        this.lastYearLeaveLeft = lastYearLeaveLeft;
    }

    public Byte getVacationNumber() {
        return vacationNumber;
    }

    public void setVacationNumber(Byte vacationNumber) {
        this.vacationNumber = vacationNumber;
    }
}