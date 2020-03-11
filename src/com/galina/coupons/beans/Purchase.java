package com.galina.coupons.beans;

import java.time.LocalDate;

public class Purchase {
    private Long id;
    private Long customerId;
    private Long companyId;
    private Long amount;
    private LocalDate timestamp;

    public Purchase(Long customerId, Long companyId, Long amount, LocalDate timestamp) {
        this.customerId = customerId;
        this.companyId = companyId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Purchase(Long id, Long customerId, Long companyId, Long amount, LocalDate timestamp) {
        this (customerId, companyId, amount, timestamp);
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate gettimestamp() {
        return timestamp;
    }

    public void settimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
