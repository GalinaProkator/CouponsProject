package com.galina.coupons.logic;

import com.galina.coupons.beans.Coupon;
import com.galina.coupons.dao.CouponsDao;

public class CouponsController {
    private CouponsDao couponsDao;

    public CouponsController(){
        this.couponsDao = new CouponsDao();
    }

    public void addCoupon (Coupon coupon) throws Exception {
        couponValidations (coupon);
        this.couponsDao.addCoupon(coupon);

    }

    private void couponValidations(Coupon coupon) throws Exception {
        if(coupon == null){
            throw new Exception("A null coupon");
        }
        if (this.couponsDao.isCouponExists(coupon.getCouponTitle())){
            throw new Exception("Can't create coupon, the coupon name already exists");
        }
        if(coupon.getCouponTitle().length() < 2){
            throw new Exception("The coupon name is too short");
        }
        if(coupon.getCouponTitle().length() > 100){
            throw new Exception("The coupon name is too long");
        }
        if(coupon.getDescription().length() < 10){
            throw new Exception("The coupon name is too short");
        }
        if(coupon.getDescription().length() > 500){
            throw new Exception("The coupon name is too long");
        }
    }

}
