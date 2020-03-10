package com.galina.coupons.dao;

import com.galina.coupons.beans.Coupon;
import com.galina.coupons.enums.CouponCategory;

import java.time.LocalDate;

public class CouponsDao {
    public void addCoupon (Coupon coupon){
        System.out.println("Coupon has been successfully added to DB");
    }

    public Coupon[] getAllCoupons(){
        LocalDate startDate1 = LocalDate.of(2020,1,1);
        LocalDate endDate1 = LocalDate.of(2020,3,23);
        LocalDate startDate2 = LocalDate.of(2020,1,1);
        LocalDate endDate2 = LocalDate.of(2020,23,12);
        LocalDate startDate3 = LocalDate.of(2020,1,1);
        LocalDate endDate3 = LocalDate.of(2020,31,12);

        Coupon coupon1 = new Coupon(1l,
                "Breakfast for two",
                CouponCategory.HORECA,
                "so tasty",
                startDate1,
                endDate1,
                3234567899l,
                123,
                "123456789");
        Coupon coupon2 = new Coupon(1l, "Elvis never left", CouponCategory.ENTERTAINMENT,
                "so good", startDate2, endDate2, 99994999999l, 444, "123456e789");
        Coupon coupon3 = new Coupon(1l, "Buy a friend", CouponCategory.GOODS,
                "robots are the future", startDate3, endDate3, 99999999959l, 888, "1234567fdg89");

        Coupon[] coupons = new Coupon[3] ;
        coupons[0] = coupon1;
        coupons[1] = coupon2;
        coupons[2] = coupon3;

        return coupons;
    }

    public boolean isCouponExists (String couponTitle){
        return false;
    }


}
