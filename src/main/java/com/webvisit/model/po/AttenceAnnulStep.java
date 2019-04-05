package com.webvisit.model.po;

public class AttenceAnnulStep {
    private Long id;

    private Long annualId;

    private Integer moreThan;

    private Integer lessThan;

    private Integer vacationDays;

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

    public Integer getMoreThan() {
        return moreThan;
    }

    public void setMoreThan(Integer moreThan) {
        this.moreThan = moreThan;
    }

    public Integer getLessThan() {
        return lessThan;
    }

    public void setLessThan(Integer lessThan) {
        this.lessThan = lessThan;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }
}
