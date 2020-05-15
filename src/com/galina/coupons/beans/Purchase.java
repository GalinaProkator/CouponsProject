package com.galina.coupons.beans;

public class Purchase {
    private Long id;
    private Long customerId;
    private Long couponId;
    private Long amount;
    private java.util.Date timestamp;

    public Purchase(Long customerId, Long couponId, Long amount) {
        this.customerId = customerId;
        this.couponId = couponId;
        this.amount = amount;
    }

    public Purchase(Long customerId, Long couponId, Long amount, java.util.Date timestamp) {
        this(customerId, couponId, amount);
        this.timestamp = timestamp;
    }

    public Purchase(Long id, Long customerId, Long couponId, Long amount, java.util.Date timestamp) {
        this (customerId, couponId, amount, timestamp);
        this.id = id;
    }

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public java.util.Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
}
