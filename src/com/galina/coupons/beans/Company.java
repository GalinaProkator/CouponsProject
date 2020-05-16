package com.galina.coupons.beans;

public class Company {
    private Long companyId;
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;

    public Company(String companyName, String companyEmail, String companyPhone, String companyAddress) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
    }

    public Company(Long companyId, String companyName, String companyEmail, String companyPhone, String companyAddress) {
        this(companyName, companyEmail, companyPhone, companyAddress);
        this.companyId = companyId;
    }

    public Company() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "Company{" +
                "comanyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }
}
