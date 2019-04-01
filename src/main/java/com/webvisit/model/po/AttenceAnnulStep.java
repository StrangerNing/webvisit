package com.webvisit.model.po;

public class AttenceAnnulStep {
    private Long id;

    private Long annualId;

    private Byte moreThan;

    private Byte lessThan;

    private Byte vacationDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnnualId() {
        return annualId;
    }

    public void setAnnualId(Long annualId) {
        this.annualId = annualId;
    }

    public Byte getMoreThan() {
        return moreThan;
    }

    public void setMoreThan(Byte moreThan) {
        this.moreThan = moreThan;
    }

    public Byte getLessThan() {
        return lessThan;
    }

    public void setLessThan(Byte lessThan) {
        this.lessThan = lessThan;
    }

    public Byte getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Byte vacationDays) {
        this.vacationDays = vacationDays;
    }
}