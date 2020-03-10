package com.galina.coupons.logic;

import com.galina.coupons.beans.Coupon;
import com.galina.coupons.dao.CouponsDao;

public class CouponsController {
    private CouponsDao couponsDao;

    public CouponsController(){
        this.couponsDao = new CouponsDao();
    }

    public void addCoupon (Coupon coupon){
        //        Validations
        if(coupon == null){

            System.out.println("A null coupon");
            return;
        }
        if (this.couponsDao.isCouponExists(coupon.getCouponTitle())){
            System.out.println("Can't create coupon, the coupon name already exists");
            return;
        }
        if(coupon.getCouponTitle().length() < 2){
            System.out.println("The coupon name is too short");
            return;
        }
        if(coupon.getCouponTitle().length() > 100){
            System.out.println("The coupon name is too long");
            return;
        }
        if(coupon.getDescription().length() < 10){
            System.out.println("The coupon name is too short");
            return;
        }
        if(coupon.getDescription().length() > 500){
            System.out.println("The coupon name is too long");
            return;
        }

        this.couponsDao.addCoupon(coupon);

    }

}
