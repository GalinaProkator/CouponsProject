package com.galina.coupons.enums;

public enum CouponCategory {
    GOODS("Goods"),
    HOTELSANDTRAVEL("Hotels and travel"),
    HORECA("Restaurants and Catering"),
    ENTERTAINMENT("Entertainment");

        private String name;

    CouponCategory(String category) {
        this.name = category;
    }

    public String getName() {
        return this.name;
    }
}
