package com.galina.coupons.enums;

public enum CouponCategory {
//    do we need these?
    GOODS("Goods"),
    HOTELS_AND_TRAVEL("Hotels and travel"),
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
