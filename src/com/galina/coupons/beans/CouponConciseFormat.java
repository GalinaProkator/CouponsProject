package com.galina.coupons.beans;

public class CouponConciseFormat {
    private Long id;
    private Long companyId;
    private String couponTitle;
    private int price;

    public CouponConciseFormat(Long companyId, String couponTitle, int price) {
        this.companyId = companyId;
        this.couponTitle = couponTitle;
        this.price = price;
    }

    public CouponConciseFormat(Long id, Long companyId, String couponTitle, int price) {
        this(companyId, couponTitle, price);
        this.id = id;
    }

    public CouponConciseFormat() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", couponTitle='" + couponTitle + '\'' +
                ", price=" + price +
                '}';
    }
}
