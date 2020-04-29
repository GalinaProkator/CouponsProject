package com.galina.coupons.beans;

public class Customer {
    private Long id;
    private User user;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public Customer(String customerName, String customerEmail, String customerPhone) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public Customer(Long id, String customerName, String customerEmail, String customerPhone) {
        this(customerName, customerEmail, customerPhone);
        this.id = id;
    }

    public Customer() {
    }


    public Long getId() {
        return this.user.getId();
    }

    public void setId(Long id) {
        user.setId(id);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                '}';
    }
}
