package com.galina.coupons.beans;

/**
 * Added comment1
 */
public class Company {
    private Long id;
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

    public Company(Long id, String companyName, String companyEmail, String companyPhone, String companyAddress) {
        this(companyName, companyEmail, companyPhone, companyAddress);
        this.id = id;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
