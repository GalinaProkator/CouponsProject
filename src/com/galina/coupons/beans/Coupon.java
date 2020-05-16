package com.galina.coupons.beans;

import com.galina.coupons.enums.CouponCategory;

public class Coupon {
    private Long id;
    private Long companyId;
    private String couponTitle;
    private CouponCategory category;
    private String description;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private Long amount;
    private int price;
    private String image;

    public Coupon(Long companyId, String couponTitle, CouponCategory category, String description, java.util.Date startDate, java.util.Date endDate, Long amount, int price, String image) {
        this.companyId = companyId;
        this.couponTitle = couponTitle;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(Long id, Long companyId, String couponTitle, CouponCategory category, String description, java.util.Date startDate, java.util.Date endDate, Long amount, int price, String image) {
        this(companyId, couponTitle, category, description, startDate, endDate, amount, price, image);
        this.id = id;
    }

    public Coupon() {
    }

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

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public CouponCategory getCategory() {
        return category;
    }

    public void setCategory(CouponCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", couponTitle='" + couponTitle + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
