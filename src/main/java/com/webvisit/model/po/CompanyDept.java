package com.webvisit.model.po;

public class CompanyDept {
    private Long id;

    private Long companyId;

    private String name;

    private Long attenceRegulationId;

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

    public Long getAttenceRegulationId() {
        return attenceRegulationId;
    }

    public void setAttenceRegulationId(Long attenceRegulationId) {
        this.attenceRegulationId = attenceRegulationId;
    }
}